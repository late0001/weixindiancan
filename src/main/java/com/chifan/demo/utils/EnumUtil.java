package com.chifan.demo.utils;

import com.chifan.demo.enums.CodeEnum;

public class EnumUtil <T>{
    public static <T extends CodeEnum> T getByCode(Integer code, Class<T> enumClass){
        for(T each: enumClass.getEnumConstants()){
            if(code.equals(each.getCode())){
                return each;
            }
        }
        return null;
    }
}
