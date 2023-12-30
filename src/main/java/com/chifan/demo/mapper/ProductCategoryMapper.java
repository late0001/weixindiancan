package com.chifan.demo.mapper;

import com.chifan.demo.pojo.ProductCategory;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ProductCategoryMapper {


//    @Insert("insert into product_category(category_name, category_type) value (#{categoryName, jdbcType=VARCHAR}, #{category_type, jdbcType=INTEGER)")
//    int insertByMap(Map<String, Object> map);
//
//    @Insert(
//            {"insert into product_category(category_name, category_type) values (#{categoryName, jdbcType=VARCHAR}, #{categoryType, jdbcType=INTEGER})"
//    })
//    @SelectKey(keyProperty = "categoryId", before = false, resultType = java.lang.Integer.class,statement = "select last_insert_id();")
    int insertByObject(ProductCategory productCategory);
////
//    @Select("select * from product_category where category_type = #{categoryType}")
//    //@ResultMap("ProductCategory")
//    ProductCategory findByCategoryType(Integer categoryType);

//    @Select("select * from product_category where category_id = #{categoryId}")
//    @Results(id = "ProductCategory" , value ={
//            @Result(column = "category_id", property = "categoryId"),
//            @Result(column = "category_name", property = "categoryName"),
//            @Result(column = "category_type", property = "categoryType"),
//            @Result(column = "create_time", property = "createTime"),
//            @Result(column = "update_time", property = "updateTime")
//    })
    ProductCategory findByCategoryId(Integer categoryId);

//    @Select("select * from product_category where category_name = #{categoryName}")
//    //@ResultMap("ProductCategory")
//    List<ProductCategory> findByCategoryName(String categoryName);

//    @Select("select * from product_category")
//    @ResultMap("ProductCategory")
    List<ProductCategory> findAll();

    //@Select("select * from product_category where product_type in ")
    List<ProductCategory> findByCategoryTypeIn(@Param("ctList") List<Integer> categoryTypeList);
//    @Update("update product_category set category_name = #{categoryName} where category_type = #{categoryType}")
//    int updateByCategoryType(@Param("categoryName") String categoryName,
//                             @Param("categoryType") Integer categoryType);
//
//    @Update("update product_category set category_name = #{categoryName} where category_type = #{categoryType}")
//    int updateByObject(ProductCategory productCategory);
//
//    @Delete("delete from product_category where category_type = #{categoryType}")
//    int deleteByCategoryType(Integer categoryType);
//
//    ProductCategory selectByCategoryType(Integer categoryType);


}
