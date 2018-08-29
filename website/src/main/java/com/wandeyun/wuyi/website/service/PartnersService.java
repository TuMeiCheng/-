package com.wandeyun.wuyi.website.service;


import com.wandeyun.wuyi.website.bean.Partners;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PartnersService {

    //查询全部合作伙伴
    Page<Partners> findall(Pageable pageable);

    //更新合作伙伴
    Partners update(Partners partners);

    //删除合作伙伴
    void delete(Integer id);
}
