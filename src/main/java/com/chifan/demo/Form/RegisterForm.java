package com.chifan.demo.Form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class RegisterForm {

    private String userName;

    private String password;

    private String userVerifyCode;
}
