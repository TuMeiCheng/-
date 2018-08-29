package com.wandeyun.wuyi.website.enums;

import lombok.Getter;

@Getter
public enum StatusEnum {


    ENABLE(0,"启用"),

    DISABLE(1,"禁用")
    ;
    private Integer code;

    private String msg;

    StatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
