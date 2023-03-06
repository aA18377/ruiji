package com.yanmou.mybatisplusstudy.controller.backendController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.incrementer.DefaultIdentifierGenerator;
import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yanmou.mybatisplusstudy.Dto.DishDto;
import com.yanmou.mybatisplusstudy.common.R;
import com.yanmou.mybatisplusstudy.pojo.Category;
import com.yanmou.mybatisplusstudy.pojo.DishFlavor;
import com.yanmou.mybatisplusstudy.pojo.Employee;
import com.yanmou.mybatisplusstudy.service.impl.CategoryServiceImpl;
import com.yanmou.mybatisplusstudy.service.impl.DishFlavorImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Auther: zhaoss
 * @Date: 2023/2/25 - 02 - 25 - 20:32
 * @Description: com.yanmou.mybatisplusstudy.controller
 * @version: 1.0
 */
@RestController
@RequestMapping("/category")
@Slf4j
public class CategoryController {
    @Autowired
    private CategoryServiceImpl service;
    @Autowired
    private DishFlavorImpl dishFlavorService;

    /**
     * 分页
     */
    @GetMapping("/page")
    public R page(@RequestParam("page") long current, @RequestParam("pageSize") long pageSize) {
        Page<Category> page = new Page<>(current, pageSize);
        QueryWrapper<Category> wrapper = new QueryWrapper<>();
        //根据id进行排序
        wrapper.orderByDesc("id");
        return R.success(service.page(page, wrapper));
    }

    /**
     * 删除
     */
    @DeleteMapping
    public R<String> delete(long id) {
        QueryWrapper<Category> wrapper = new QueryWrapper<>();
        wrapper.eq("id", id);
        boolean remove = service.remove(wrapper);
        if (remove) {
            return R.success(null);
        }
        return R.error(null);
    }

    /**
     * 修改
     */
    @PutMapping
    public R<String> update(@RequestBody Category category, HttpServletRequest request) {
        UpdateWrapper<Category> wrapper = new UpdateWrapper<>();
        wrapper.set("sort", category.getSort());
        wrapper.set("name", category.getName());
        //wrapper.set("update_time", LocalDateTime.now());
        Employee loginUser = (Employee) request.getSession().getAttribute("loginUser");
        //wrapper.set("update_user",loginUser.getId());
        wrapper.eq("id", category.getId());
        boolean update = service.update(category, wrapper);
        if (update) {
            return R.success(null);
        }
        return R.error(null);
    }


    /**
     * 新增菜品类别---套餐类别
     */
    @PostMapping
    public R<String> addCategory(@RequestBody Category category, Model model) {
        //category.setCreateTime(new Timestamp(System.currentTimeMillis()));
        // category.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        Employee loginUser = (Employee) model.getAttribute("loginUser");
        // category.setCreateUser(loginUser.getId());
        //category.setUpdateUser(loginUser.getId());
        IdentifierGenerator identifierGenerator = new DefaultIdentifierGenerator();
        long autoId = identifierGenerator.nextId(category).longValue();
        category.setId(autoId);
        boolean save = service.save(category);
        if (save) {
            return R.success(null);
        }
        return R.error(null);
    }

    /**
     * 菜品类别下拉框选项
     */
    @GetMapping("/list")
    public R<List<Category>> categoryList(Long type) {
        QueryWrapper<Category> wrapper = new QueryWrapper<>();
        wrapper.eq(type != null,"type",type);
        List<Category> list = service.list(wrapper);
        return R.success(list);
    }
}
