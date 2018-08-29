package com.wandeyun.wuyi.website.service.impl;

import com.wandeyun.wuyi.website.bean.Dynamic;
import com.wandeyun.wuyi.website.enums.ResultEnum;
import com.wandeyun.wuyi.website.exception.WuYiException;
import com.wandeyun.wuyi.website.repository.DynamicRepository;
import com.wandeyun.wuyi.website.service.DynamicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * 最新动态serviceimpl
 */
@Service
@Transactional
public class DynamicServiceImpl implements DynamicService {

    @Autowired
    private DynamicRepository dynamicRepository;

    /**
     * 查询所有
     * @return
     */
    public Page<Dynamic> findAll(Pageable pageable){
         Page<Dynamic> pageables = dynamicRepository.findAll(pageable);
        return pageables;
    }

    /**
     * 新增
     * @return
     */
    @Override
    public Dynamic insert(Dynamic dynamic) {
        return dynamicRepository.save(dynamic);
    }

    /** 根据dyn_id查询
     * @param: [dyn_id]
     * @return: com.wandeyun.wuyi.website.bean.Dynamic */
    @Override
    public Dynamic findByDyn_Id(Integer dyn_id) {
        Dynamic opt = dynamicRepository.findOne(dyn_id);
        if (opt == null) {
            throw new WuYiException(ResultEnum.DYNAMIC_NOT_EXIST.getCode(),ResultEnum.DYNAMIC_NOT_EXIST.getMessage());
        }
        return opt;
    }

    /** 修改动态
     * @param: [dynamic]
     * @return: com.wandeyun.wuyi.website.bean.Dynamic */
    @Override
    public Dynamic update(Dynamic dynamic) {
        dynamic.setCreateTime(this.dynamicRepository.findOne(dynamic.getId()).getCreateTime());
        return this.dynamicRepository.save(dynamic);
    }

    /* 删除动态
     * @param: [id]
     * @return: void */
    @Override
    public void delete(Integer id) {
        this.dynamicRepository.delete(id);
    }


}
