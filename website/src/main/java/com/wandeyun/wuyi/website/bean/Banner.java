package com.wandeyun.wuyi.website.bean;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
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
 * 轮播图管理表
 */
@Data
@Entity
@DynamicUpdate
@DynamicInsert(true)
@EntityListeners(AuditingEntityListener.class)
public class Banner  implements Serializable {

    private static final long serialVersionUID = 7826771578483819214L;

    //轮播图id
    @Id
    @GeneratedValue                //生成策略为自增
    private Integer  id;

    //轮播图片路径
    private String banPath;

    //标题
    private String banTitle;

    //点击图片后跳转地址
    private String banUrl;

    //内容
    private String banContent;

    //最后修改人id  0: 代表新提交
    private Integer modi;

    //最后修改时间
    @LastModifiedDate
    private Date  updateTime;

    //状态
    private Integer banStatus;  //0:启用  1：禁用

    // Entity中不映射成列的字段得加@Transient 注解，不加注解会映射成列


}
