package com.chifan.demo.service;

import com.chifan.demo.pojo.ProductCategory;

import java.util.List;

public interface CategoryService {
    ProductCategory findOne(Integer categoryId);
    List<ProductCategory> findAll();
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
    int save(ProductCategory productCategory);
}
