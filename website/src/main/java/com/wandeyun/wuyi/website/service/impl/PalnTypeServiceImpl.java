package com.wandeyun.wuyi.website.service.impl;

import com.wandeyun.wuyi.website.bean.PlanType;
import com.wandeyun.wuyi.website.repository.PlanTypeRepository;
import com.wandeyun.wuyi.website.service.PlanTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PalnTypeServiceImpl implements PlanTypeService {

    @Autowired
    private PlanTypeRepository planTypeRepository;

    @Override
    /** 查询全部
     * @param: []
     * @return: java.util.List<com.wandeyun.wuyi.website.bean.PlanType> */
    public List<PlanType> findAll() {
        return planTypeRepository.findAll();
    }

}
