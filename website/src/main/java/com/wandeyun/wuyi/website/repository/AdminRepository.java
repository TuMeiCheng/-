package com.wandeyun.wuyi.website.repository;

import com.wandeyun.wuyi.website.bean.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Integer> {

    Admin findByAccount(String account);
}
