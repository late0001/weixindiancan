package com.chifan.demo.service.impl;

import com.chifan.demo.dataobject.SellerInfo;
import com.chifan.demo.enums.ResultEnum;
import com.chifan.demo.exception.SellException;
import com.chifan.demo.mapper.SellerInfoMapper;
import com.chifan.demo.pojo.Seller;
import com.chifan.demo.repository.SellerInfoRepository;
import com.chifan.demo.service.SellerService;
import com.chifan.demo.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SellerServiceImpl implements SellerService {
    //@Autowired
    //private SellerInfoRepository repository;

    @Autowired
    private SellerInfoMapper mapper;

    @Override
    public SellerInfo findSellerInfoByOpenid(String openid) {
        return null;
        //return repository.findByOpenid(openid);
    }

    @Override
    public int createSeller(Seller seller) {
        return mapper.createUser(seller);
    }

    @Override
    public List<Seller> findByUserName(String userName) {
        return mapper.selectByUserName(userName);
    }

    @Override
    public Seller validLoginInfo(String userName, String password) {
        Seller seller = mapper.selectOne(userName);
        if(seller == null){
            throw new SellException(ResultEnum.USER_NOT_EXIST);
        }
        String pass = MD5Util.md5Digest(password, 234);
        if(!pass.equals(seller.getPassword())){
            throw new SellException(ResultEnum.USER_PASS_INCORRECT);
        }
        return seller;
    }


}
