package com.wandeyun.wuyi.website.service;

import com.wandeyun.wuyi.website.bean.UserInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserInfoService {

    //查询所有
    Page<UserInfo> findAll(Pageable request);

    //新增
    boolean insert(UserInfo userInfo);

}
