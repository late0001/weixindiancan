package com.chifan.demo.service.impl;

import com.chifan.demo.pojo.ProductCategory;
import com.chifan.demo.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
@SpringBootTest
@Slf4j
class CategoryServiceImplTest {
    @Autowired
    private CategoryServiceImpl categoryService;
    @Test
    void findOne() {
        ProductCategory productCategory = categoryService.findOne(1);
        assertNotNull(productCategory);

    }

    @Test
    void findAll() {
        List<ProductCategory> productCategoryList = categoryService.findAll();
        assertNotEquals(0, productCategoryList.size());
    }

    @Test
    void findByCategoryTypeIn() {
        List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn(Arrays.asList(1,2,3,4));
        assertNotEquals(0, productCategoryList.size());
    }

    @Test
    void save() throws Exception{
        ProductCategory productCategory = new ProductCategory("女生j8专享", 11);
        int result = categoryService.save(productCategory);
        log.info("hello categoryId = "+ productCategory.getCategoryId());
        assertNotNull(result);
    }
}