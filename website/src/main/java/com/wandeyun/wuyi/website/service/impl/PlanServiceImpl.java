package com.wandeyun.wuyi.website.service.impl;

import com.wandeyun.wuyi.website.bean.Plan;
import com.wandeyun.wuyi.website.bean.PlanType;
import com.wandeyun.wuyi.website.dto.PlanTypeDTO;
import com.wandeyun.wuyi.website.enums.ResultEnum;
import com.wandeyun.wuyi.website.enums.StatusEnum;
import com.wandeyun.wuyi.website.exception.WuYiException;
import com.wandeyun.wuyi.website.repository.PlanRepository;
import com.wandeyun.wuyi.website.repository.PlanTypeRepository;
import com.wandeyun.wuyi.website.service.PlanService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

/*
 *@author tmc
 *@date 2018/7/21 13:40
 * 解决方案service实现类
 */

@Service
@Transactional
@Slf4j
public class PlanServiceImpl implements PlanService {

    @Autowired
    private PlanRepository planRepository;

    @Autowired
    private PlanTypeRepository typeRepository;

 /** 查询全部解决方案
  * @param: []
  * @return: java.util.List<com.wandeyun.wuyi.website.bean.Plan> */
    @Override
    public List<Plan> findAll() {
        return this.planRepository.findAll();
    }

    /** 根据方案类型查询所有解决方案分组
     * @param: []
     * @return: Map<Plan,List<Plan>> */
    @Override
    public List<PlanTypeDTO> findPlanListAll() {
        List<PlanTypeDTO> dtoList = new ArrayList<>();
        //查询所有解决方案类型
        List<PlanType> pType_List = this.typeRepository.findAll();
        if(pType_List != null  && pType_List.size() > 0){
            for (PlanType planType : pType_List) {
                //根据解方案类型id查询该类型下的所有方案
                PageRequest request = new PageRequest(0, 1000);

                Page<Plan> planPage = this.findPlanByTypeId(planType.getId(),request);

                PlanTypeDTO planTypeDTO = new PlanTypeDTO();
                planTypeDTO.setPlanList(planPage.getContent());
                planTypeDTO.setId(planType.getId());
                planTypeDTO.setTypeName(planType.getTypeName());
                dtoList.add(planTypeDTO);
            }
        }
        return dtoList;
    }

    /** 根据方案类型Id查询解决方案
     * @param: [typeId]
     * @return: com.wandeyun.wuyi.website.dto.PlanTypeDTO */
    @Override
    public Page<Plan> findPlanByTypeId(Integer typeId, Pageable pageable) {
        PlanTypeDTO planTypeDTO = new PlanTypeDTO();
        PlanType opt_PlanType = this.typeRepository.findOne(typeId);
        if (opt_PlanType == null) {
            log.error("【根据方案类型Id查询解决方案】 方案类型无法获取 typeId={}",typeId);
            throw new WuYiException(ResultEnum.FIND_PLANBY_TYPEID_ERROR);
        }
        planTypeDTO.setTypeName(opt_PlanType.getTypeName());
        planTypeDTO.setId(opt_PlanType.getId());
        Page<Plan> planPage =  this.planRepository.findByPtypeId(typeId,pageable);
        return planPage;
    }

    //根据方案id查询方案
    @Override
    public Plan getPlanById(Integer id) {
        return this.planRepository.findOne(id);
    }


    /** 新增  and /修改
     * 解决方案
     * @param: [plan]
     * @return: com.wandeyun.wuyi.website.bean.Plan */
    @Override
    public Plan insert(Plan plan) {
        if(plan.getId() != null){  //修改
            //将创建时间保存回去
            plan.setCreateTime(this.planRepository.findOne(plan.getId()).getCreateTime());
        }
        return this.planRepository.save(plan);
    }

    /* 删除解决方案
     * @param: [planId]
     * @return: java.lang.Boolean */
    @Override
    public Boolean delete(Integer planId) {

        this.planRepository.delete(planId);
        return  true;
    }

    /* 获取所有解决方案类型
     * @param: []
     * @return: java.util.List<com.wandeyun.wuyi.website.bean.PlanType> */
    @Override
    public List<PlanType> getTypeSelect() {
        return this.typeRepository.findAll();
    }

    public Page<Plan> findPlanByTypeId2(Integer typeId, Pageable pageable) {
        PlanTypeDTO planTypeDTO = new PlanTypeDTO();
        PlanType opt_PlanType = this.typeRepository.findOne(typeId);
        if (opt_PlanType == null) {
            log.error("【根据方案类型Id查询解决方案】 方案类型无法获取 typeId={}",typeId);
            throw new WuYiException(ResultEnum.FIND_PLANBY_TYPEID_ERROR);
        }
        planTypeDTO.setTypeName(opt_PlanType.getTypeName());
        planTypeDTO.setId(opt_PlanType.getId());
        Page<Plan> planPage =  this.planRepository.findByPtypeIdAndPlanStatus(typeId, StatusEnum.ENABLE.getCode(),pageable);
        return planPage;
    }


}
