package com.wandeyun.wuyi.website.service.impl;

import com.wandeyun.wuyi.website.bean.Banner;
import com.wandeyun.wuyi.website.repository.BannerRepository;
import com.wandeyun.wuyi.website.service.BannerService;
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
public class BannerServiceImpl implements BannerService {

    @Autowired
    private BannerRepository bannerRepository;


    /** 查询全部
     * @param: []
     * @return: java.util.List<com.wandeyun.wuyi.website.bean.Banner> */
    @Override
    public Page<Banner> findAll(Pageable pageable) {
        Page<Banner> bannerPage = bannerRepository.findAll(pageable);
        return bannerPage;
    }

    /** 新增
     * @param: [banner]
     * @return: com.wandeyun.wuyi.website.bean.Banner */
    @Override
    public Banner insert(Banner banner) {
        return bannerRepository.save(banner);
    }

    /* 删除
     * @param: [banId]
     * @return: com.wandeyun.wuyi.website.bean.Banner */
    @Override
    public void delete (Integer banId){
        this.bannerRepository.delete(banId);
    }
}
