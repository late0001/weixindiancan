package com.chifan.demo.VO;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResultVO<T> implements Serializable {

    private static final long serialVersionUID = -9038751496177950975L;
    private Integer code;
    private String msg;
    private T data;
}
