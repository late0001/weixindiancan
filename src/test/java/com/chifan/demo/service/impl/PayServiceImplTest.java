package com.chifan.demo.service.impl;

import com.chifan.demo.dto.OrderDTO;
import com.chifan.demo.service.OrderService;
import com.chifan.demo.service.PayService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
@SpringBootTest
@Slf4j
class PayServiceImplTest {

    @Autowired
    private OrderService orderService;

    @Autowired
    private PayService payService;

    @Test
    void create() {
        OrderDTO orderDTO = orderService.findOne("1499097366838352541");
        payService.create(orderDTO);
    }

    @Test
    void testNotify() {
    }

    @Test
    void refund() {
    }
}