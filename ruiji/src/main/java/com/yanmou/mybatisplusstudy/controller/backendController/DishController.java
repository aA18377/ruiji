package com.yanmou.mybatisplusstudy.controller.backendController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.incrementer.DefaultIdentifierGenerator;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yanmou.mybatisplusstudy.Dto.DishDto;
import com.yanmou.mybatisplusstudy.common.R;
import com.yanmou.mybatisplusstudy.pojo.Category;
import com.yanmou.mybatisplusstudy.pojo.Dish;
import com.yanmou.mybatisplusstudy.pojo.DishFlavor;
import com.yanmou.mybatisplusstudy.service.impl.CategoryServiceImpl;
import com.yanmou.mybatisplusstudy.service.impl.DishFlavorImpl;
import com.yanmou.mybatisplusstudy.service.impl.DishServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: zhaoss
 * @Date: 2023/2/26 - 02 - 26 - 16:37
 * @Description: com.yanmou.mybatisplusstudy.controller
 * @version: 1.0
 */
@Controller
@Slf4j
@RequestMapping("/dish")
public class DishController {
    @Autowired
    private DishServiceImpl service;
    @Autowired
    private CategoryServiceImpl categoryService;
    @Autowired
    private DishFlavorImpl dishFlavorService;
    @Value("${ruiji.basePath}")
    private String basePath;

    /**
     * 分页
     */
    @ResponseBody
    @GetMapping("/page")
    public R<Page<DishDto>> page(@RequestParam("page") long current, @RequestParam("pageSize") long pageSize, @Nullable @RequestParam("name") String name) {
        Page<Dish> page = new Page<>(current, pageSize);
        QueryWrapper<Dish> wrapper = new QueryWrapper<>();
        //根据id进行排序
        wrapper.orderByDesc("id");
        //当name不为空时进行模糊查询
        wrapper.like(name != null, "name", name);


        Page<Dish> dishPage = service.page(page, wrapper);
        //最终结果集
        Page<DishDto> dishDtoPage = new Page<>();
        //拷贝records外的属性
        BeanUtils.copyProperties(dishPage, dishDtoPage, "records");
        //最终结果的records
        List<DishDto> dishDtoList = new ArrayList<>();
        List<Dish> records = dishPage.getRecords();
        //将老结果集遍历拷贝出来
        for (Dish record : records) {
            DishDto dishDto = new DishDto();
            BeanUtils.copyProperties(record, dishDto);
            //查询CategoryName
            Category DBcategory = categoryService.getById(record.getCategoryId());
            dishDto.setCategoryName(DBcategory.getName());
            dishDtoList.add(dishDto);
        }
        dishDtoPage.setRecords(dishDtoList);

        return R.success(dishDtoPage);
    }

    /**
     * 删除  批量删除
     * 删除dish表，dish_flavor表，上传目录
     */
    @DeleteMapping
    @ResponseBody
    public R<String> delete(long[] ids) {
        List<Long> idList = new ArrayList<>();
        QueryWrapper<DishFlavor> dishFlavorQueryWrapper = new QueryWrapper<>();
        int count = 0;
        boolean delete = false;
        for (long id : ids) {
            idList.add(id);
            dishFlavorQueryWrapper.eq("dish_id", id);
            boolean remove2 = dishFlavorService.remove(dishFlavorQueryWrapper);
            if (remove2) {
                count++;
            }
            dishFlavorQueryWrapper.clear();

            Dish getById = service.getById(id);
            File file = new File(basePath + getById.getImage());
            if (file.exists()) {
                delete = file.delete();
                if (!delete) {
                    log.info("删除图片失败");
                }
            }
        }

        boolean remove = service.removeByIds(idList);
        if (remove && count == idList.size() && delete) {
            return R.success(null);
        }
        return R.error(null);
    }

    /**
     * 起售-停售
     */
    @PostMapping("/status/{status}")
    @ResponseBody
    public R<String> status(long[] ids, @PathVariable("status") long status) {
        UpdateWrapper<Dish> wrapper = new UpdateWrapper<>();
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
     * 菜品页
     */
    @GetMapping("/list.html")
    public String dishList() {
        return "backend/page/food/list";
    }

    /**
     * 修改页
     */
    @GetMapping("/add.html")
    public String addView() {
        return "backend/page/food/add";
    }

    /**
     * 修改页数据渲染
     */
    @ResponseBody
    @GetMapping("/{id}")
    public R<DishDto> adda(@PathVariable("id") long id) {
        Dish dish = service.getById(id);
        DishDto dishDto = new DishDto();
        BeanUtils.copyProperties(dish, dishDto);
        QueryWrapper<Category> wrapper = new QueryWrapper<>();
        wrapper.eq("id", dishDto.getCategoryId());
        String categoryName = categoryService.getOne(wrapper).getName();
        dishDto.setCategoryName(categoryName);
        List<DishFlavor> flavors = service.getUpdateData(id);
        dishDto.setFlavors(flavors);
        log.info("对象：" + dishDto);
        return R.success(dishDto);
    }

    /**
     * 新增菜品
     */
    @ResponseBody
    @PostMapping
    @CacheEvict(value = "dishCache",key = "'dish_'+#dishDto.categoryId")
    public R<String> addDish(@RequestBody DishDto dishDto) {
        boolean dishSave = service.save(dishDto);
        for (DishFlavor flavor : dishDto.getFlavors()) {
            flavor.setDishId(dishDto.getId());
        }
        //批量插入
        boolean flavorSava = dishFlavorService.saveBatch(dishDto.getFlavors());
        if (dishDto.getFlavors()!=null && dishSave && flavorSava){
            return R.success(null);
        }
        if (dishSave && flavorSava) {
            return R.success(null);
        }
        return R.error(null);
    }




    /**
     * 执行菜品修改
     */
    @PutMapping
    @ResponseBody
    @CacheEvict(value = "dishCache",key = "'dish_'+#dishDto.categoryId")
    public R<String> addTodo(@RequestBody DishDto dishDto) {
        UpdateWrapper<Dish> dishWrapper = new UpdateWrapper<>();
        dishWrapper.eq("id", dishDto.getId());
        boolean update = service.update(dishDto, dishWrapper);
        //修改结果计数器
        int dishFlavorFlagCount = 0;
        UpdateWrapper<DishFlavor> dishFlavorWrapper = new UpdateWrapper<>();
        //dish_floar Id列表
        dishFlavorWrapper.eq("dish_id", dishDto.getId());
        List<DishFlavor> list = dishFlavorService.list(dishFlavorWrapper);
        for (int i = 0; i < dishDto.getFlavors().size(); i++) {
            dishFlavorWrapper.clear();
            dishFlavorWrapper.eq("id", list.get(i).getId());
            dishFlavorWrapper.set("name", dishDto.getFlavors().get(i).getName());
            dishFlavorWrapper.set("value", dishDto.getFlavors().get(i).getValue());
            boolean dishFlavorFlag = dishFlavorService.update(dishFlavorWrapper);
            if (dishFlavorFlag) {
                dishFlavorFlagCount++;
            }
        }
        if (dishFlavorFlagCount == dishDto.getFlavors().size() && update) {
            return R.success(null);
        }

        return R.error(null);
    }




    /**
     * 套餐菜品选择
     * @param categoryId
     * @param name
     * @return
     */
    @GetMapping("/list")
    @ResponseBody
    @Cacheable(value = "dishCache",key = "'dish_'+#categoryId",unless = "#result.code.equals(0)")
    public R<List<DishDto>> dishSetmealTest(Long categoryId,String name) {
        QueryWrapper<Dish> wrapper = new QueryWrapper<>();
        wrapper.eq(categoryId != null,"category_id", categoryId);
        wrapper.like(name!=null,"name",name);
        List<Dish> list = service.list(wrapper);
        List<DishDto> dtoList = new ArrayList<>();
        QueryWrapper<DishFlavor> queryWrapper = new QueryWrapper<>();
        for (Dish dish : list) {
            DishDto dishDto = new DishDto();
            BeanUtils.copyProperties(dish,dishDto);
            queryWrapper.eq("dish_id",dishDto.getId());
            List<DishFlavor> dishFlavorList = dishFlavorService.list(queryWrapper);
            dishDto.setFlavors(dishFlavorList);
            dtoList.add(dishDto);
            queryWrapper.clear();
        }
        return R.success(dtoList);
    }



@Test
    public void test(){
    System.out.println(new Timestamp(System.currentTimeMillis()-5000));
    System.out.println(new Timestamp(System.currentTimeMillis()));
}
}
