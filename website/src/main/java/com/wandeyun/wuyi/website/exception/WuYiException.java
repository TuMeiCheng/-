package com.wandeyun.wuyi.website.exception;


import com.wandeyun.wuyi.website.enums.ResultEnum;

public class  WuYiException extends RuntimeException{

    private Integer code;

    public WuYiException( ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
        }

    public WuYiException(Integer code, String message) {
        super(message);
        this.code = code;
        }

























}
