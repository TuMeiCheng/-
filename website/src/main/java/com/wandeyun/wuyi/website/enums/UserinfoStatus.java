package com.wandeyun.wuyi.website.enums;

import lombok.Getter;

@Getter
public enum UserinfoStatus {

    NEW_SUBMIT(0,"未读"),

    READER(1,"已处理")
    ;

    private Integer value;

    private  String msg;

     UserinfoStatus(Integer code, String message){
        this.value = code;
        this.msg= message;
    }

}
