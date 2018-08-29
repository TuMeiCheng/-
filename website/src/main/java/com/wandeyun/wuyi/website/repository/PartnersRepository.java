package com.wandeyun.wuyi.website.repository;

import com.wandeyun.wuyi.website.bean.Partners;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/* 合作伙伴dao */
public interface PartnersRepository extends JpaRepository<Partners,Integer> {

    
}
