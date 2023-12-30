package com.chifan.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HelloController {
    @GetMapping("/index")
    @ResponseBody
    public Map<String, String> hello(){
        Map map = new HashMap<String, String>();
        map.put("北京", "很远");
        map.put("深圳", "南方城市");
        return map;
    }
}
