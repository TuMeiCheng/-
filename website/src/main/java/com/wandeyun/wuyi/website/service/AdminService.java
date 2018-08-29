package com.wandeyun.wuyi.website.service;

import com.wandeyun.wuyi.website.bean.Admin;

public interface AdminService {

    //根据账号密码查询
    Admin findBypwd(String  account,String pwd);

    //添加系统用户
    Admin addAdmin(Admin admin);

    //删除系统用户

}
