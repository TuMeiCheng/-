package com.wandeyun.wuyi.website.service;

import com.wandeyun.wuyi.website.bean.Banner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 *@author tmc
 *@date 2018/7/21 12:21
 *
 */
public interface BannerService {

    //查询所有轮播图
    Page<Banner> findAll(Pageable pageable);

    //新增
    Banner insert(Banner banner);

    //删除
    void delete(Integer banId);



}
