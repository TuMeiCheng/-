package com.wandeyun.wuyi.website.service;

import com.wandeyun.wuyi.website.bean.Plan;
import com.wandeyun.wuyi.website.bean.PlanType;
import com.wandeyun.wuyi.website.dto.PlanTypeDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface PlanService{

    //查询全部
    List<Plan> findAll();

    /** 根据方案类型查询所有解决方案分组
     * @param: []
     * @return: Map<Plan,List<Plan>> */
    List<PlanTypeDTO> findPlanListAll();

    //根据方案类型查询所有解决方案
    Page<Plan> findPlanByTypeId(Integer typeId, Pageable pageable);

    //根据方案id查询方案
    Plan getPlanById(Integer id);

    //新增
    Plan insert(Plan plan);

    //删除
    Boolean delete(Integer planId);


    //获取所有方案类型
    List<PlanType> getTypeSelect();
}
