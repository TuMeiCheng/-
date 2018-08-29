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

/**
 *
 * 最新动态表
 */

@Data
@Entity
@DynamicUpdate
@DynamicInsert
@EntityListeners(AuditingEntityListener.class)
public class Dynamic implements Serializable {

    private static final long serialVersionUID = -4039980377437158179L;

    //最新动态id
    @Id
    @GeneratedValue                //生成策略为自增
    private Integer  id;

    //图片路径
    private String dynaPath;

    //标题
    private String dynaTitle;

    //点击图片后跳转地址
    private String dynaUrl;

    //摘要内容
    private String dynaContent;

    //最后修改人id  0: 代表新提交
    private Integer modi;

    //创建时间
    @CreatedDate
    private Date createTime;

    //最后修改时间
    @LastModifiedDate
    private Date updateTime;

    //状态
    private Integer dynaStatus;  //0:启用  1：禁用


}
