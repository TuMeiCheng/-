package com.wandeyun.wuyi.website.enums;

import lombok.Getter;

@Getter
public enum DynamicEnum {

    ;
    private Integer code;

    private String msg;

    DynamicEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
