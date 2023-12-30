package com.chifan.demo.service.impl;

import com.chifan.demo.dataobject.OrderDetail;
import com.chifan.demo.dto.OrderDTO;
import com.chifan.demo.enums.OrderStatusEnum;
import com.chifan.demo.enums.PayStatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@Slf4j
@SpringBootTest
class OrderServiceImplTest {
    @Autowired
    private OrderServiceImpl orderService;
    private final String BUYER_OPENID = "1101110";
    private final String ORDER_ID = "1703058400209478632";
    @Test
    void create() {
        for( int i= 0 ; i < 30; i++ )
        {
            String hello[] = {"牛头山", "马王山", "狗头山", "鸡公山", "车公庙", "鸡毛店"};
            int idx = (int)(Math.random() * 6);
            OrderDTO orderDTO = new OrderDTO();
            orderDTO.setBuyerName( i + "师兄");
            orderDTO.setBuyerAddress(hello[idx]);
            orderDTO.setBuyerPhone("1866582942"+ String.valueOf(idx));
            orderDTO.setBuyerOpenid(BUYER_OPENID);

            //购物车
            List<OrderDetail> orderDetailList = new ArrayList<>();

            OrderDetail o1 = new OrderDetail();
            o1.setProductId("123458");
            o1.setProductQuantity(i);
            orderDetailList.add(o1);
//        OrderDetail o2 = new OrderDetail();
//        o2.setProductId("123457");
//        o2.setProductQuantity(2);
//        orderDetailList.add(o2);

            orderDTO.setOrderDetailList(orderDetailList);

            OrderDTO result = orderService.create(orderDTO);
            log.info("【创建订单】 result = {}", result);
            assertNotNull(result);
        }

    }

    @Test
    void findOne() {
        OrderDTO result = orderService.findOne(ORDER_ID);
        log.info("【查询单个订单】 result = {}", result);
        assertEquals(ORDER_ID, result.getOrderId());
    }

    @Test
    void findList() {
        PageRequest request = PageRequest.of(0, 2);
        Page<OrderDTO> orderDTOPage = orderService.findList(BUYER_OPENID, request);
        assertNotEquals(0, orderDTOPage.getTotalElements());
    }

    @Test
    void cancel() {
        OrderDTO orderDTO = orderService.findOne(ORDER_ID);
        OrderDTO result =  orderService.cancel(orderDTO);
        assertEquals(OrderStatusEnum.CANCEL.getCode(), result.getOrderStatus());
    }

    @Test
    void finish() {
        OrderDTO orderDTO = orderService.findOne(ORDER_ID);
        OrderDTO result =  orderService.finish(orderDTO);
        assertEquals(OrderStatusEnum.FINISHED.getCode(), result.getOrderStatus());
    }

    @Test
    void paid() {
        OrderDTO orderDTO = orderService.findOne(ORDER_ID);
        OrderDTO result =  orderService.paid(orderDTO);
        assertEquals(PayStatusEnum.SUCCESS.getCode(), result.getPayStatus());
    }

    @Test
    void list() {
        PageRequest request = PageRequest.of(0, 2);
        Page<OrderDTO> orderDTOPage = orderService.findList(request);
        assertNotEquals(0, orderDTOPage.getTotalElements());
        //assertTrue(orderDTOPage.getTotalElements() > 0, "查询所有订单列表");
    }
}