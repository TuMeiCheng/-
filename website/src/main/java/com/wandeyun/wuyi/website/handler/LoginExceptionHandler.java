package com.wandeyun.wuyi.website.handler;

import com.wandeyun.wuyi.website.VO.ResultVO;
import com.wandeyun.wuyi.website.exception.LoginException;
import com.wandeyun.wuyi.website.exception.WuYiException;
import com.wandeyun.wuyi.website.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/*
 *@author tmc
 *@date 2018/8/29 11:43
 * 统一异常处理
 */
@ControllerAdvice
@Slf4j
public class LoginExceptionHandler {

    /* 登录拦截异常处理
     * @param: []
     * @return: com.wandeyun.wuyi.website.VO.ResultVO */
    @ResponseBody
    @ExceptionHandler(LoginException.class)
    public ResultVO handlerAuthorizeException() {
        log.info("登录状态失效捕获");
       return ResultVOUtil.error(1005,"登录状态失效，请重新登录");
    }
}
