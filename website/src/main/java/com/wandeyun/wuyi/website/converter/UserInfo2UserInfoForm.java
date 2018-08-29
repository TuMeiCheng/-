package com.wandeyun.wuyi.website.converter;

import com.wandeyun.wuyi.website.bean.UserInfo;
import com.wandeyun.wuyi.website.form.UserInfoForm;

public class UserInfo2UserInfoForm {

    public static  UserInfo converter(UserInfoForm userInfoForm){
        UserInfo userInfo = new UserInfo();

        userInfo.setUserName(userInfoForm.getUsername());
        userInfo.setEmail(userInfoForm.getEmail());
        userInfo.setPhone(userInfoForm.getPhone());
        userInfo.setContent(userInfoForm.getContent());

        return userInfo;
    }
}
