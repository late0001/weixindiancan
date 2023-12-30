package com.chifan.demo.service.impl;

import com.chifan.demo.dataobject.ProductInfo;
import com.chifan.demo.enums.ProductStatusEnum;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ProductServiceImplTest {
    @Autowired
    private ProductServiceImpl productService;
    @Test
    void findOne() {
        ProductInfo productInfo = productService.findOne("123456");
        assertEquals("123456", productInfo.getProductId());
    }

    @Test
    void findUpAll() {
        List<ProductInfo> productInfoList = productService.findUpAll();
        assertNotEquals(0, productInfoList.size());
    }

    @Test
    void findAll() throws  Exception{
        //Sort sort = new Sort(Sort.Direction.DESC, new ArrayList<String>("product_id", "product_name"));
        PageRequest request = PageRequest.of(0, 2);
        Page<ProductInfo> productInfoPage = productService.findAll(request);
        assertNotEquals(0, productInfoPage.getTotalElements());
    }

    @Test
    void save() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("123457");
        productInfo.setProductName("肉蟹煲");
        productInfo.setProductPrice(new BigDecimal(3.8));
        productInfo.setProductStock(100);
        productInfo.setProductDescription("很皮的蟹");
        productInfo.setProductIcon("http://xxxxxxxx.jpg");
        productInfo.setProductStatus(ProductStatusEnum.DOWN.getCode());
        productInfo.setCategoryType(2);
        ProductInfo result = productService.save(productInfo);
        assertNotNull(result);
    }

    @Test
    void onSale(){
        ProductInfo result = productService.onSale("123456");
        assertEquals(ProductStatusEnum.UP, result.getProductStatusEnum());
    }

    @Test
    void offSale() {
        ProductInfo result = productService.offSale("123456");
        assertEquals(ProductStatusEnum.DOWN, result.getProductStatusEnum());
    }
}