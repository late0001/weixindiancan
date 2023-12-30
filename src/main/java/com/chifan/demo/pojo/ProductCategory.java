package com.chifan.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;
@Data
@NoArgsConstructor
//@AllArgsConstructor
public class ProductCategory {
        /** 类目id. */
        //@Id
        //@GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer categoryId;

        /** 类目名字. */
        private String categoryName;

        /** 类目编号. */
        private Integer categoryType;

        private Date createTime;

        private Date updateTime;

//    public ProductCategory() {
//    }

    public ProductCategory(String categoryName, Integer categoryType) {
        this.categoryName = categoryName;
        this.categoryType = categoryType;
    }

}
