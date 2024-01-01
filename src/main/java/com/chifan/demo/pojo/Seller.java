package com.chifan.demo.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class Seller {
    private String sellerId;
    private String userName;
    private String password;
    private String nickName;
    private String openId;
    private Date createTime;

    private Date updateTime;
}
