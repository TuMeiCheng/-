package com.wandeyun.wuyi.website.controller;

import com.wandeyun.wuyi.website.VO.ResultVO;
import com.wandeyun.wuyi.website.bean.Admin;
import com.wandeyun.wuyi.website.service.AdminService;
import com.wandeyun.wuyi.website.utils.CookieUtil;
import com.wandeyun.wuyi.website.utils.MD5Util;
import com.wandeyun.wuyi.website.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.mail.Session;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.UUID;

/*
 *@author tmc
 *@date 2018/8/21 14:00
 *
 */
@Controller()
@RequestMapping("/api/admin")
@Slf4j
public class AdminController {

    @Autowired
    private AdminService adminService;

    //登录
    @RequestMapping("/login")
    @ResponseBody
    public ResultVO login(@RequestParam("account") String account,
                          @RequestParam("pwd") String pwd,
                          HttpServletResponse response) {

        Admin admin = adminService.findBypwd(account,pwd);
        //验证密码
        if (admin != null && MD5Util.verify(pwd, admin.getPwd())) {
            log.info("系统用户登录成功！ account={}",account);
            String token = UUID.randomUUID().toString();
            //3. 设置token至cookie
            CookieUtil.set(response, "token", token, 20);
            return ResultVOUtil.success(admin);
        }else {
            log.info("系统用户登录失败！ account={}",account);
            return ResultVOUtil.error(10002,"登录失败，密码或账户错误");
        }
    }

    //添加系统用户
    @RequestMapping("addAdmin")
    @ResponseBody
    public ResultVO addAdmin(@Valid Admin admin) {
        try {
            Admin result = this.adminService.addAdmin(admin);
            if (result != null) {
                log.info("系统用户添加成功！ admin={}", admin.toString());
                return ResultVOUtil.success(admin);
            }
        } catch (Exception e) {
            log.info("系统用户添加失败！ admin={}", admin.toString());
            return ResultVOUtil.error(1001,"添加失败："+e.getMessage());
        }
        return ResultVOUtil.success(admin);
    }



    //退出登录
    @RequestMapping("/logout")
    @ResponseBody
    public ResultVO logout( HttpServletResponse response,
                            HttpServletRequest request) {
        //1. 从cookie里查询
        Cookie cookie = CookieUtil.get(request, "token");
        if (cookie != null) {
            //2. 清除cookie
            CookieUtil.set(response, "token", null, 0);
        }else {
            return ResultVOUtil.error(1002, "您当前尚未登录");
        }

        return ResultVOUtil.success();
    }


    //跳转到文本编辑器页面
    @RequestMapping("/edit")
    public String edit() {
       return "/kindeditor/jsp/bk";
    }

    @RequestMapping("/getEdit")
    public String getEdit(@RequestParam("title") String title,
                       @RequestParam("content") String content) {
        System.out.println("title:"+title);
        System.out.println("content:"+content);
        return "/kindeditor/jsp/bk";

    }


}
