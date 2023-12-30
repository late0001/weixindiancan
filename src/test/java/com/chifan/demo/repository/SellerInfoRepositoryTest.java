package com.chifan.demo.repository;

import com.chifan.demo.dataobject.SellerInfo;
import com.chifan.demo.utils.KeyUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class SellerInfoRepositoryTest {
    @Autowired
    private SellerInfoRepository repository;

    @Test
    void save() {
        SellerInfo sellerInfo = new SellerInfo();
        sellerInfo.setSellerId(KeyUtil.genUniqueKey());
        sellerInfo.setOpenid("abc");
        sellerInfo.setUsername("admin");
        sellerInfo.setPassword("admin");
        SellerInfo result = repository.save(sellerInfo);
        assertNotNull(result);
    }
    @Test
    void findByOpenid() {
        SellerInfo result = repository.findByOpenid("abc");
        assertEquals("abc", result.getOpenid());
    }
}