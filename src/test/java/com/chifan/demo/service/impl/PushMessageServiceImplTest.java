package com.chifan.demo.service.impl;

import com.chifan.demo.dto.OrderDTO;
import com.chifan.demo.service.OrderService;
import com.chifan.demo.service.PushMessageService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class PushMessageServiceImplTest {

    @Autowired
    private OrderService orderService;

    @Autowired
    private PushMessageService pushMessageService;

    @Test
    void orderStatus() {
        OrderDTO orderDTO = orderService.findOne("1703323491064201527");
        pushMessageService.orderStatus(orderDTO);
    }
}