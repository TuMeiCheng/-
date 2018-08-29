package com.wandeyun.wuyi.website.dto;

import com.wandeyun.wuyi.website.bean.Plan;
import lombok.Data;

import java.util.List;

@Data
public class PlanTypeDTO {

    //解决方案类型id
    private Integer  id;

    //类型名称
    private String typeName;

    //该解决方案类型下的所有解决方案集合
    private List<Plan> planList;

}
