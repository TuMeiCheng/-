package com.wandeyun.wuyi.website.bean;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.wandeyun.wuyi.website.utils.Date2LongSerializer;
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
 * 解决方案表
 */
@Data
@Entity
@DynamicUpdate
@DynamicInsert
@EntityListeners(AuditingEntityListener.class)
public class Plan  implements Serializable {

    private static final long serialVersionUID = -2005446036107043589L;

    @Id
    @GeneratedValue                //生成策略为自增
    private Integer id;

    //方案名称
    private String planName;

    //方案所属类型
    private Integer ptypeId;

    //点击跳转地址
    private String planUrl;

    //摘要内容
    private String planContent;

    //最后修改人id  0: 代表新提交
    private Integer modi;

    //创建时间
   // @JsonSerialize(using = Date2LongSerializer.class)
    @CreatedDate
    private Date createTime;


    //最后修改时间
   // @JsonSerialize(using = Date2LongSerializer.class)
    @LastModifiedDate
    private Date updateTime;

    //状态
    private Integer planStatus;  //0:启用  1：禁用

    //图片路径
    private String planPath;
}
