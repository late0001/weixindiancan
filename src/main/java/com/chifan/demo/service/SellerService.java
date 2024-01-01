package com.chifan.demo.service;

import com.chifan.demo.dataobject.SellerInfo;
import com.chifan.demo.pojo.Seller;

import java.util.List;

public interface SellerService {
    /**
     * 通过openid查询卖家端信息
     * @param openid
     * @return
     */
    SellerInfo findSellerInfoByOpenid(String openid);

    int createSeller(Seller seller);

    List<Seller> findByUserName(String userName);

    Seller validLoginInfo(String userName, String password);

}
