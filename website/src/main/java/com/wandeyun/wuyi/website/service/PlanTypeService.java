package com.wandeyun.wuyi.website.service;

import com.wandeyun.wuyi.website.bean.PlanType;

import java.util.List;

/*
 *@author tmc
 *@date 2018/7/21 14:35
 * 解决方案服务层
 */
public interface PlanTypeService {

    /** 查询全部
     * @param: []
     * @return: java.util.List<com.wandeyun.wuyi.website.bean.PlanType> */
    List<PlanType> findAll();



}
