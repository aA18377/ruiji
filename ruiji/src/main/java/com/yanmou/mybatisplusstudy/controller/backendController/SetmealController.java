package com.yanmou.mybatisplusstudy.controller.backendController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.incrementer.DefaultIdentifierGenerator;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yanmou.mybatisplusstudy.Dto.SetmealDto;
import com.yanmou.mybatisplusstudy.common.R;
import com.yanmou.mybatisplusstudy.pojo.Dish;
import com.yanmou.mybatisplusstudy.pojo.Setmeal;
import com.yanmou.mybatisplusstudy.pojo.SetmealDish;
import com.yanmou.mybatisplusstudy.service.impl.SetmealDishServiceImpl;
import com.yanmou.mybatisplusstudy.service.impl.SetmealServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: zhaoss
 * @Date: 2023/3/2 - 03 - 02 - 14:36
 * @Description: com.yanmou.mybatisplusstudy.controller
 * @version: 1.0
 */
@Slf4j
@Controller
@RequestMapping("/setmeal")
public class SetmealController {
    @Autowired
    private SetmealServiceImpl service;
    @Autowired
    private SetmealDishServiceImpl dishService;

    /**
     * 分页
     */
    @ResponseBody
    @GetMapping("/page")
    public R<Page<Setmeal>> page(@RequestParam("page") long current, @RequestParam("pageSize") long pageSize, String name) {
        Page<Setmeal> page = new Page<>(current, pageSize);
        QueryWrapper<Setmeal> wrapper = new QueryWrapper<>();
        wrapper.like(name != null, "name", name);
        //根据id进行排序
        wrapper.orderByDesc("id");
        return R.success(service.page(page, wrapper));
    }

    /**
     * 起售-停售
     */
    @PostMapping("/status/{status}")
    @ResponseBody
    public R<String> status(long[] ids, @PathVariable("status") long status) {
        UpdateWrapper<Setmeal> wrapper = new UpdateWrapper<>();
        int count = 0;
        for (long id : ids) {
            wrapper.eq("id", id);
            wrapper.set("status", status);
            if (service.update(wrapper)) {
                count++;
            }
            wrapper.clear();
        }
        if (count == ids.length) {
            return R.success(null);
        }
        return R.error(null);
    }


    /**
     * 批量删除
     */
    @DeleteMapping
    @ResponseBody
    public R<String> delete(long[] ids) {
        List<Long> idList = new ArrayList<>();
        for (long id : ids) {
            idList.add(id);
        }
        boolean remove = service.removeByIds(idList);
        if (remove) {
            return R.success(null);
        }
        return R.error(null);
    }

    /**
     * 修改页--添加页
     */
    @GetMapping("/add.html")
    public String add() {
        return "backend/page/combo/add";
    }

    /**
     * 新增套餐
     */
    @PostMapping
    @ResponseBody
    public R<String> addTODO(@RequestBody SetmealDto setmealDto) {
        Long setmealId = new DefaultIdentifierGenerator().nextId(setmealDto);
        setmealDto.setId(setmealId);
        boolean save = service.save(setmealDto);
        int count = 0;
        for (SetmealDish setmealDish : setmealDto.getSetmealDishes()) {
            setmealDish.setId(new DefaultIdentifierGenerator().nextId(setmealDto));
            setmealDish.setSetmealId(setmealId+"");
            boolean save1 = dishService.save(setmealDish);
            if (save1){
                count++;
            }
        }
        if (count == setmealDto.getSetmealDishes().size() && save){
            return  R.success(null);
        }
        return R.error(null);
    }

    /**
     * 新增页数据渲染
     */
    @GetMapping("/{id}")
    @ResponseBody
    public R<SetmealDto> addView(@PathVariable("id")Long id){
        Setmeal setmeal = service.getById(id);
        SetmealDto setmealDto = new SetmealDto();
        BeanUtils.copyProperties(setmeal,setmealDto);
        QueryWrapper<SetmealDish> setmealDishQueryWrapper = new QueryWrapper<>();
        setmealDishQueryWrapper.eq("setmeal_id",id);
        List<SetmealDish> list = dishService.list(setmealDishQueryWrapper);
        setmealDto.setSetmealDishes(list);
        return R.success(setmealDto);
    }

    /**
     * 修改
     */
    @PutMapping
    @ResponseBody
    public R<String> update(@RequestBody SetmealDto setmealDto){
        UpdateWrapper<Setmeal> setmealUpdateWrapper = new UpdateWrapper<>();
        setmealUpdateWrapper.eq("id",setmealDto.getId());
        boolean update = service.update(setmealDto,setmealUpdateWrapper);
        List<SetmealDish> setmealDishes = setmealDto.getSetmealDishes();
        UpdateWrapper<SetmealDish> wrapper = new UpdateWrapper<>();
        wrapper.eq("setmeal_id",setmealDto.getId());
        List<SetmealDish> list = dishService.list(wrapper);
        int count = 0;
        for (int i = 0; i < setmealDishes.size(); i++) {
            wrapper.clear();
            wrapper.eq("id",list.get(i).getId());
            boolean update1 = dishService.update(setmealDishes.get(i), wrapper);
            if (update1){
                count++;
            }
        }
        if (count == setmealDishes.size() && update){
            return R.success(null);
        }
        return R.error(null);
    }

    /**
     * 套餐菜品列表
     */
    @GetMapping("/list")
    @ResponseBody
    public R<List<Setmeal>> list(Setmeal setmeal){
        QueryWrapper<Setmeal> wrapper = new QueryWrapper<>();
        wrapper.eq("Category_id",setmeal.getCategoryId());
        wrapper.eq("status",setmeal.getStatus());
        List<Setmeal> list = service.list(wrapper);
        return R.success(list);
    }

}
