package com.wandeyun.wuyi.website.bean;


import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

//合作伙伴
@Data
@Entity
@DynamicUpdate
@EntityListeners(AuditingEntityListener.class)
public class Partners implements Serializable{
    private static final long serialVersionUID = -4655646362405834065L;

    @Id
    @GeneratedValue             //生成策略为自增
    private Integer id;    //合作伙伴id

    //合作伙伴名称
    private String parnersName;

    //跳转url
    private String url;

    //图标地址
    private String iconPath;

    //最后修改时间
    @LastModifiedDate
    private Date updateTime;

}
