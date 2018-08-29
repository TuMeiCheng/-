package com.wandeyun.wuyi.website.service.impl;

import com.wandeyun.wuyi.website.bean.Partners;
import com.wandeyun.wuyi.website.repository.PartnersRepository;
import com.wandeyun.wuyi.website.service.PartnersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@Slf4j
public class PartnersServiceImpl implements PartnersService {

    @Autowired
    private PartnersRepository  partnersRepository;


    /* 查询全部合作伙伴
     * @param: []
     * @return: java.util.List<com.wandeyun.wuyi.website.bean.Partners> */
    @Override
    public Page<Partners> findall(Pageable pageable) {
        return this.partnersRepository.findAll(pageable);
    }

    /* 更新合作伙伴
     * @param: [partners]
     * @return: com.wandeyun.wuyi.website.bean.Partners */
    @Override
    public Partners update(Partners partners) {
        return this.partnersRepository.save(partners);
    }

    /* 删除合作伙伴
     * @param: [id]
     * @return: void */
    @Override
    public void delete(Integer id) {
        this.partnersRepository.delete(id);
    }
}
