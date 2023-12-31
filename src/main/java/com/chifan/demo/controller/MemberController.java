package com.chifan.demo.controller;

import com.google.code.kaptcha.Producer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FastByteArrayOutputStream;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Controller
@Slf4j
public class MemberController {
    @Autowired
    private Producer kaptchaProducer;

    @GetMapping("verify_code")
    public void GenVerifyCode(HttpServletRequest request, HttpServletResponse response){

        String imagecode = kaptchaProducer.createText();
        // 生成图片
        BufferedImage image = kaptchaProducer.createImage(imagecode);
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-store,no-cache, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/png");
        request.getSession().setAttribute("kaptchaVerifyCode", imagecode);

        try {
            ServletOutputStream os = response.getOutputStream();
            ImageIO.write(image,"png",os);
            log.info("verify code: "+ request.getSession().getAttribute("kaptchaVerifyCode"));
            os.flush();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
