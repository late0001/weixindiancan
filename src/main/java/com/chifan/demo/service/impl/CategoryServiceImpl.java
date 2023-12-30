package com.chifan.demo.service.impl;

import com.chifan.demo.mapper.ProductCategoryMapper;
import com.chifan.demo.pojo.ProductCategory;
import com.chifan.demo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    //@Autowired
    //private ProductCategoryRepository repository;

    @Autowired
    ProductCategoryMapper mapper;

    @Override
    public ProductCategory findOne(Integer categoryId) {
        //return repository.findById(categoryId).orElse(null);
        return mapper.findByCategoryId(categoryId);
    }


    @Override
    public List<ProductCategory> findAll() {
        //return repository.findAll();
        return mapper.findAll();
    }

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList) {

        return mapper.findByCategoryTypeIn(categoryTypeList);
    }

    @Override
    public int save(ProductCategory productCategory) {
        return mapper.insertByObject(productCategory);
    }

//    @Override
//    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList) {
//        return repository.findByCategoryTypeIn(categoryTypeList);
//    }

//    @Override
//    public ProductCategory save(ProductCategory productCategory) {
//        return repository.save(productCategory);
//    }
}
