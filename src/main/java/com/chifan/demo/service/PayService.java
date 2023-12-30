package com.chifan.demo.service;

import com.chifan.demo.dto.OrderDTO;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.wxpay.response.WxPayRefundResponse;

public interface PayService {
    PayResponse create(OrderDTO orderDTO);

    PayResponse notify(String notifyData);

    WxPayRefundResponse refund(OrderDTO orderDTO);
}
