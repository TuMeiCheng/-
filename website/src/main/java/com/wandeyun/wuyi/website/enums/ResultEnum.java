package com.wandeyun.wuyi.website.enums;

import com.wandeyun.wuyi.website.bean.Dynamic;
import lombok.Getter;

@Getter
public enum ResultEnum {


    SUBMIT_ERROR(10, "提交失败"),

    SUBMIT_SUCCESS(11,"提交成功"),

    ADD_DYNAMIC_ERROR(12,"添加动态失败！"),

    PARAM_ERROR(13,"参数错误"),

    DYNAMIC_NOT_EXIST(14,"动态不存在"),

    DYNAMIC_UPDATE_ERROR(15,"动态修改失败"),

    FIND_PLANBY_TYPEID_ERROR(16,"查询解决方案失败，方案类型id错误！"),

    FILE_UPLOAD_EXIST(17,"文件上传，失败，找不到file！")
    ;

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
