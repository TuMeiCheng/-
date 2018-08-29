package com.wandeyun.wuyi.website.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;

import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@DynamicInsert
@DynamicUpdate
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 6488464051487573794L;

    @Id                             //主键
    @GeneratedValue                //生成策略为自增
    private  Integer id;

    @Column(name="username")
    private String userName;      //用户姓名

    private String phone;        //手机

    private String email;        //邮箱

    @CreatedDate
    private Date createTime;     //创建时间

    private Integer status;     //状态  0：新提交  1：已处理

    private Integer modifyBy;    //最后修改人id  0: 代表新提交

    private String content;      //内容

    @Override
    public String toString() {
        return "UserInfo{" +
                "userinfoId=" + id +
                ", userNaem='" + userName + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", createTime=" + createTime +
                ", status=" + status +
                ", modifyBy=" + modifyBy +
                ", content='" + content + '\'' +
                '}';
    }
}