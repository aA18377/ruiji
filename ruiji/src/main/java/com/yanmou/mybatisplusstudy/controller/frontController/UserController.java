package com.yanmou.mybatisplusstudy.controller.frontController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.incrementer.DefaultIdentifierGenerator;
import com.yanmou.mybatisplusstudy.common.R;
import com.yanmou.mybatisplusstudy.pojo.User;
import com.yanmou.mybatisplusstudy.service.impl.DishFlavorImpl;
import com.yanmou.mybatisplusstudy.service.impl.UserSeriveImpl;
import com.yanmou.mybatisplusstudy.utils.CodeUtil;
import com.yanmou.mybatisplusstudy.utils.SendSms;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: zhaoss
 * @Date: 2023/3/3 - 03 - 03 - 17:44
 * @Description: com.yanmou.mybatisplusstudy.controller.frontController
 * @version: 1.0
 */
@Controller
@Slf4j
@SessionAttributes("{visitUser}")
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserSeriveImpl userSerive;
    @Autowired
    private DishFlavorImpl dishFlavorService;
    //默认关闭
    @Value("${ruiji.msgEnable}")
    private boolean msgEnable;
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 登录页
     */
    @GetMapping("/loginView")
    public String login() {
        return "front/page/login";
    }

    /**
     * 验证码发送
     */
    @PostMapping("/sendMsg")
    @ResponseBody
    public R<String> sendMsg(@RequestBody User user, HttpSession session) {
        //手机号码
        String phone = user.getPhone();
        log.info("手机号码:" + phone);
        if (phone != null && !"".equals(phone)) {
            //生成随机验证码
            String code = CodeUtil.nextId(4);
            //使用生成的验证码并调用阿里云短信服务
            //丐版直接用控制台就好了
            if (msgEnable) {
                try {
                    SendSms.sendMsg(code, phone);
                } catch (ExecutionException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
            redisTemplate.opsForValue().set("loginCode",code,5, TimeUnit.MINUTES);
            log.info("验证码:" + code);

            //放行
            return R.success("验证码已发送，请注意查收");
        }
        return R.error("手机号码格式不正确");
    }


    /**
     * 执行登录
     *
     * @param session 后续登录用户的获取都是用注解。但注解只能获取session中的数据而不能清除
     */
    @PostMapping("/login")
    @ResponseBody
    public R<String> loginTODO(@RequestBody Map<String, Object> map, HttpSession session) {
        //验证码是否正确
        String code = map.get("code").toString();
        String phone = map.get("phone").toString();
        String phoneCode = (String) redisTemplate.opsForValue().get("loginCode");
        if (phoneCode == null){
            log.info("验证码没有正常缓存");
        }

        if (phoneCode != null && code.equals(phoneCode)) {

            //数据库查询是否有该用户
            QueryWrapper<User> wrapper = new QueryWrapper<>();
            wrapper.eq("phone", phone);
            User user = userSerive.getOne(wrapper);
            if (user == null) {//无，设置初始id和name
                wrapper.clear();
                user = new User();
                Long userId = new DefaultIdentifierGenerator().nextId(map);
                user.setPhone(phone);
                user.setId(userId);
                user.setStatus(1);
                user.setName("瑞吉用户" + userId);
                boolean save = userSerive.save(user);
                if (!save) {
                    session.setAttribute("visitUser", user);
                    log.info("注册新用户失败");
                    return R.error("系统繁忙，请稍后再试");
                }
            }
            if (user.getStatus() != 1) {
                return R.error("账户被禁用");
            }
            session.setAttribute("visitUser", user);
            return R.success("登录成功");
        }
        return R.error("验证码错误");
    }

    /**
     * 主页
     */
    @GetMapping("/index.html")
    public String index() {
        return "front/index";
    }

    /**
     * 用户个人中心
     */
    @GetMapping("/user.html")
    public String userView() {
        return "front/page/user";
    }

    /**
     * 用户地址管理
     */
    @GetMapping("/address.html")
    public String addressView() {
        return "front/page/address";
    }

    /**
     * 全部订单页
     *
     * @return
     */
    @GetMapping("/order.html")
    public String orderView() {
        return "front/page/order";
    }

}
