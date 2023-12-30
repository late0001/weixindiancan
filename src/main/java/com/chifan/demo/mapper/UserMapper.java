package com.chifan.demo.mapper;

import com.chifan.demo.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("select * from users where age=#{age}")
    User findByAge(int age);
}
