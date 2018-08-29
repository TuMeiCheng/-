package com.wandeyun.wuyi.website.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Data;

import java.io.Serializable;

/**
 * 解决方案类型表
 */

@Data
@Entity
public class PlanType  implements Serializable {

    private static final long serialVersionUID = -3774965231615096826L;

    //解决方案类型id
    @Id
    @GeneratedValue                //生成策略为自增
    private Integer  id;

    //类型名称
    private String typeName;



}
