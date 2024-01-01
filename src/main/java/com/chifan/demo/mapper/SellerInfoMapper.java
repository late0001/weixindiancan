package com.chifan.demo.mapper;

import com.chifan.demo.pojo.Seller;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SellerInfoMapper {
    public int createUser(Seller seller);

    List<Seller> selectByUserName(String userName);

    Seller selectOne(String userName);
}
