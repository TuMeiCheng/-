package com.wandeyun.wuyi.website.repository;

import com.wandeyun.wuyi.website.bean.Plan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PlanRepository extends JpaRepository<Plan,Integer>{

   // @Query("SELECT * from plan where ptype_id = ?1")
   Page<Plan> findByPtypeIdAndPlanStatus(Integer ptype_Id,Integer planStatus, Pageable pageable);

   Page<Plan> findByPtypeId(Integer ptype_Id, Pageable pageable);
}
