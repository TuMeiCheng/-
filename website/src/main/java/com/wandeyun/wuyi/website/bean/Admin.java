package com.wandeyun.wuyi.website.bean;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 *
 * 系统用户表
 */
@Data
@Entity
public class Admin implements Serializable {

    private static final long serialVersionUID = 978764623463614412L;

    @Id
    @GeneratedValue             //生成策略为自增
    private Integer id;

    //账号
    private String account;

    //密码
    private String pwd;

    //加密密码的盐
    private String salt;

    //状态 0可用  10冻结  20 删除
    private Integer status;

}
