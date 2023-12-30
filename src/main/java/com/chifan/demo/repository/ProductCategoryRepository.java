package com.chifan.demo.repository;

import com.chifan.demo.pojo.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductCategoryRepository /*extends JpaRepository<ProductCategory, Integer> */{
//public interface ProductCategoryRepository {
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
}
