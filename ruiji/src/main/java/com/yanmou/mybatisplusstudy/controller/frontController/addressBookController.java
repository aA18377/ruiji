package com.yanmou.mybatisplusstudy.controller.frontController;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.yanmou.mybatisplusstudy.common.R;
import com.yanmou.mybatisplusstudy.pojo.AddressBook;
import com.yanmou.mybatisplusstudy.pojo.User;
import com.yanmou.mybatisplusstudy.service.impl.AddressBookServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Auther: zhaoss
 * @Date: 2023/3/5 - 03 - 05 - 14:31
 * @Description: com.yanmou.mybatisplusstudy.controller.frontController
 * @version: 1.0
 */
@Controller
@Slf4j
@RequestMapping("/addressBook")
public class addressBookController {
    @Autowired
    private AddressBookServiceImpl addressBookService;

    /**
     * 地址簿
     *
     * @return
     */
    @GetMapping("/list")
    @ResponseBody
    public R<List<AddressBook>> list(@SessionAttribute("visitUser") User user) {
        QueryWrapper<AddressBook> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",user.getId());
        List<AddressBook> list = addressBookService.list(wrapper);
        return R.success(list);
    }

    /**
     * 地址修改
     */
    @GetMapping("/address-edit.html")
    public String addView() {
        return "front/page/address-edit";
    }

    /**
     * 修改数据
     */
    @GetMapping("{id}")
    @ResponseBody
    public R<AddressBook> addData(@PathVariable("id") Long id) {
        AddressBook byId = addressBookService.getById(id);
        if (byId != null) {
            return R.success(byId);
        }
        return R.error("查无地址信息");
    }

    /**
     * 执行地址添加
     */
    @PostMapping
    @ResponseBody
    public R<String> add(@RequestBody AddressBook book, @SessionAttribute("visitUser") User user) {
        book.setIsDefault(0);
        book.setUserId(user.getId());
        boolean save = addressBookService.save(book);
        if (save) {
            return R.success(null);
        }
        return R.error(null);
    }

    /**
     * 执行地址修改
     */
    @PutMapping
    @ResponseBody
    public R<String> addTODO(@RequestBody AddressBook book) {
        boolean b = addressBookService.updateById(book);
        if (b) {
            return R.success(null);
        }
        return R.error(null);
    }

    /**
     * 设置默认地址
     */
    @PutMapping("/default")
    @ResponseBody
    public R<String> setdefault(@RequestBody AddressBook book,@SessionAttribute("visitUser") User user) {
        UpdateWrapper<AddressBook> wrapper = new UpdateWrapper<>();
        wrapper.set("is_default",0);
        wrapper.eq("user_id",user.getId());
        boolean update = addressBookService.update(wrapper);
        wrapper.clear();
        wrapper.set("is_default",1);
        wrapper.eq("id",book.getId());
        boolean update1 = addressBookService.update(wrapper);
        if (update1 && update){
            return R.success(null);
        }
        return R.error(null);
    }

    /**
     * 获取默认地址
     */
    @GetMapping("/default")
    @ResponseBody
    public R<AddressBook> getDefault(@SessionAttribute("visitUser") User user){
        QueryWrapper<AddressBook> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",user.getId());
        wrapper.eq("is_default",1);
        AddressBook one = addressBookService.getOne(wrapper);
        return R.success(one);
    }
}
