package com.chifan.demo.repository;

import com.chifan.demo.dataobject.OrderMaster;
import com.chifan.demo.enums.OrderStatusEnum;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
@SpringBootTest
class OrderMasterRepositoryTest {

    @Autowired
    private OrderMasterRepository repository;

    private final String OPENID = "sigui";
    @Test
    public void saveTest(){
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("123456");
        orderMaster.setBuyerName("二师兄");
        orderMaster.setBuyerOpenid(OPENID);
        orderMaster.setBuyerPhone("18665829425");
        orderMaster.setBuyerAddress("湖南省郴州市永兴县油市镇禾向村4组");
        //orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setOrderAmount(new BigDecimal(3.2));
        OrderMaster result = repository.save(orderMaster);
        assertNotNull(result);
    }

    @Test
    void findByBuyerOpenid() {
        PageRequest request = PageRequest.of(0, 3);
        Page<OrderMaster>  result = repository.findByBuyerOpenid(OPENID, request);
        assertNotEquals(0, result.getTotalElements());
        //System.out.println(result.getTotalElements());
    }
}