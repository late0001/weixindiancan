package com.chifan.demo.service.impl;

import com.chifan.demo.dataobject.SellerInfo;
import com.chifan.demo.pojo.Seller;
import com.chifan.demo.service.SellerService;
import com.chifan.demo.utils.KeyUtil;
import com.chifan.demo.utils.MD5Util;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Slf4j
class SellerServiceImplTest {

    private static final String openid = "abc";

    @Autowired
    private SellerService sellerService;

    @Test
    void findSellerInfoByOpenid() {
        SellerInfo result = sellerService.findSellerInfoByOpenid(openid);
        assertEquals(openid, result.getOpenid());
    }
    @Test
    void createSeller(){
        Seller seller = new Seller();
        seller.setSellerId(KeyUtil.genUniqueKey());
        seller.setUserName("921751743");
        seller.setOpenId("1234567777");
        seller.setPassword("OK12345");
        seller.setNickName("他妈的死鬼");
        int result = sellerService.createSeller(seller);
        assertTrue(result > 0);

    }

    @Test
    void findByUserName(){
        String username = "921751743";
        List<Seller> sellerList = sellerService.findByUserName(username);
        assertNotEquals(0, sellerList.size() );
    }

    @Test
    void validLoginInfo(){
        String username = "123457";
        String password = "123457";
        Seller result = sellerService.validLoginInfo(username, password);
        assertNotNull(result);
    }
}