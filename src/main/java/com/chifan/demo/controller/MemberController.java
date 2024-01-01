package com.chifan.demo.controller;

import com.chifan.demo.Form.RegisterForm;
import com.chifan.demo.VO.ResultVO;
import com.chifan.demo.config.ProjectUrlConfig;
import com.chifan.demo.constant.CookieConstant;
import com.chifan.demo.constant.RedisConstant;
import com.chifan.demo.dataobject.SellerInfo;
import com.chifan.demo.enums.ResultEnum;
import com.chifan.demo.exception.SellException;
import com.chifan.demo.pojo.Seller;
import com.chifan.demo.service.SellerService;
import com.chifan.demo.utils.CookieUtil;
import com.chifan.demo.utils.KeyUtil;
import com.chifan.demo.utils.MD5Util;
import com.chifan.demo.utils.ResultVOUtil;
import com.google.code.kaptcha.Producer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.util.FastByteArrayOutputStream;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.w3c.dom.stylesheets.LinkStyle;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Controller
@Slf4j
public class MemberController {
    @Autowired
    private Producer kaptchaProducer;

    @Autowired
    private SellerService sellerService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private ProjectUrlConfig projectUrlConfig;

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

    @GetMapping("/register")
    public ModelAndView showRegister()
    {
        return new ModelAndView("user/register");
    }

    @PostMapping("/registe")
    @ResponseBody
    public ResultVO registe(RegisterForm form, HttpServletRequest request){
        Seller seller = new Seller();
        seller.setNickName(form.getUserName());
        seller.setUserName(form.getUserName());
        String verifyCode = (String) request.getSession().getAttribute("kaptchaVerifyCode");
        String userVerifiyCode  = form.getUserVerifyCode();
        if(userVerifiyCode == null || verifyCode == null || !verifyCode.equals(userVerifiyCode)){
            return ResultVOUtil.error(ResultEnum.VERIFY_CODE_INCORRECT.getCode(),
                    ResultEnum.VERIFY_CODE_INCORRECT.getMessage());
        }
        log.info("[用户注册] Param username = " + form.getUserName());
        List<Seller> result= sellerService.findByUserName(form.getUserName());
        if(result.size() > 0){
            return ResultVOUtil.error(ResultEnum.USER_EXIST.getCode(),
                    ResultEnum.USER_EXIST.getMessage());
        }
        seller.setSellerId(KeyUtil.genUniqueKey());
        String passEncry = MD5Util.md5Digest(form.getPassword(), 234);
        seller.setPassword(passEncry);
        int variantNumb = new Random().nextInt(10000)+ 10000;
        String openid = "no" + String.valueOf(variantNumb);
        seller.setOpenId(openid);

        int iResult = sellerService.createSeller(seller);
        if(iResult <= 0)
            return ResultVOUtil.error(ResultEnum.REGISTER_FAIL.getCode(),
                    ResultEnum.REGISTER_FAIL.getMessage());
        return ResultVOUtil.success();

    }

    @GetMapping("/login")
    public ModelAndView login() {

        return new ModelAndView("user/login");

    }

    @PostMapping("/check_login")
    @ResponseBody
    public ResultVO login(@RequestParam("username") String userName,
                              @RequestParam("password") String password,
                              HttpServletResponse response,
                              HttpSession session,
                              Map<String, Object> map) {
        //ResultVO result= null;
        if(userName == null || password == null){
            return ResultVOUtil.error(ResultEnum.LOGIN_FAIL.getCode(),
                    ResultEnum.LOGIN_FAIL.getMessage());
        }
        //对用户名和密码注意过滤非法字符
        //1. openid去和数据库里的数据匹配
        //SellerInfo sellerInfo = sellerService.findSellerInfoByOpenid(openid);
        // 1. 校验登录信息
        Seller seller = null;
        try {
            seller = sellerService.validLoginInfo(userName, password);
            session.setAttribute("loginMember", seller);
        }catch(SellException exception){
            return ResultVOUtil.error(exception.getCode(), exception.getMessage());
        }
        //2. 设置token至redis
        String token = UUID.randomUUID().toString();
        Integer expire = RedisConstant.EXPIRE;
        String openid = seller.getOpenId();
        redisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_PREFIX, token), openid, expire, TimeUnit.SECONDS);

        //3. 设置token至cookie
        CookieUtil.set(response, CookieConstant.TOKEN, token, expire);

        return ResultVOUtil.success();

    }
}
