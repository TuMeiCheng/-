package com.wandeyun.wuyi.website.service;

import com.wandeyun.wuyi.website.bean.Dynamic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface DynamicService {

    //查询所有动态
    Page<Dynamic> findAll(Pageable pageable);

    //新增
    Dynamic insert(Dynamic dynamic);

    //根据dyn_id查询
    Dynamic findByDyn_Id(Integer dyn_id);

    //修改动态
    Dynamic update(Dynamic dynamic);

    //删除动态
    void delete(Integer id);

}
