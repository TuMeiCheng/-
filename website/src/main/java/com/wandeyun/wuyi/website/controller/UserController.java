package com.wandeyun.wuyi.website.controller;

import com.wandeyun.wuyi.website.VO.ResultVO;
import com.wandeyun.wuyi.website.bean.Admin;
import com.wandeyun.wuyi.website.bean.UserInfo;
import com.wandeyun.wuyi.website.converter.UserInfo2UserInfoForm;
import com.wandeyun.wuyi.website.exception.WuYiException;
import com.wandeyun.wuyi.website.form.UserInfoForm;
import com.wandeyun.wuyi.website.service.UserInfoService;
import com.wandeyun.wuyi.website.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.wandeyun.wuyi.website.enums.ResultEnum;
import sun.applet.Main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sound.midi.MetaEventListener;
import javax.swing.text.StyledEditorKit;
import javax.validation.Valid;
import java.util.List;

/*
 *@author tmc
 *@date 2018/7/21 12:41
 * 联系我们
 *
 */
@RestController
@RequestMapping("/api/userinfo")
@Slf4j
public class UserController {

    @Autowired
    private  UserInfoService userInfoService;

    //查询全部
    @GetMapping("/list")
    public ResultVO getList(@RequestParam(value = "page", defaultValue = "0") Integer page,
                        @RequestParam(value = "size", defaultValue = "10") Integer size){
        PageRequest request = new PageRequest(page, size);
        Page<UserInfo> userInfos =  this.userInfoService.findAll(request);
        return ResultVOUtil.success(userInfos);
    }


    /* 联系我们
     * @param: [userInfoForm, bindingResult]
     * @return: com.wandeyun.wuyi.website.VO.ResultVO<java.lang.Boolean> */
    @PostMapping("/insert")
    public ResultVO<Boolean> insert(@Valid UserInfoForm userInfoForm,
                                    BindingResult bindingResult,
                                    HttpServletRequest request,
                                    HttpServletResponse response){

        HttpSession session = request.getSession();
        String  phone = (String )session.getAttribute("phone");
        if (!StringUtils.isEmpty(phone)){
            System.out.println(phone);
            System.out.println(session.getMaxInactiveInterval());
            log.error("【联系我们】用户重复提交, userInfoForm={}", userInfoForm);
            return ResultVOUtil.error(1002,"请不要重复提交");
        }
        //校验参数
        if (bindingResult.hasErrors()) {
            log.error("【联系我们】参数不正确, userInfoForm={}", userInfoForm);
            return ResultVOUtil.error(1002,bindingResult.getFieldError().getDefaultMessage());
        }
        session.setAttribute("phone",userInfoForm.getPhone());
        //两分钟内不可以重复提交
        session.setMaxInactiveInterval(1200);
        try {
            //添加到数据库
            UserInfo userInfo = UserInfo2UserInfoForm.converter(userInfoForm);
            Boolean bln =  userInfoService.insert(userInfo);
            return ResultVOUtil.success(bln);
        }catch (Exception e){
            return ResultVOUtil.error(1001,"服务器异常，请稍后重试。");
        }

    }


}
