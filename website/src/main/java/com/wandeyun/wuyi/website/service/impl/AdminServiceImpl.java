package com.wandeyun.wuyi.website.service.impl;

import com.wandeyun.wuyi.website.bean.Admin;
import com.wandeyun.wuyi.website.repository.AdminRepository;
import com.wandeyun.wuyi.website.service.AdminService;
import com.wandeyun.wuyi.website.utils.MD5Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@Slf4j
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;


    /* 根据账户查询
     * @param: [account]
     * @return: com.wandeyun.wuyi.website.bean.Admin */
    @Override
    public Admin findBypwd(String account,String pwd) {
        Admin admin = adminRepository.findByAccount(account);
        return admin;
    }

    /* 添加系统用户
     * @param: [admin]
     * @return: com.wandeyun.wuyi.website.bean.Admin */
    @Override
    public Admin addAdmin(Admin admin) {
        //给密码用md5加密
        admin.setPwd(MD5Util.generate(admin.getPwd()));
        admin.setStatus(0);
        return this.adminRepository.save(admin);
    }
}
