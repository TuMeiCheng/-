package com.wandeyun.wuyi.website.form;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

@Data
public class UserInfoForm {

    @NotEmpty(message = "姓名必填")
    private String username;    //用户姓名

    @NotEmpty(message = "手机必填")
    private String phone;      //手机

    @NotEmpty(message = "邮箱必填")
    private String email;      //邮箱

    @NotEmpty(message = "内容必填")
    private String content;   //内容
}
