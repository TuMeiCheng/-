package com.wandeyun.wuyi.website.aspect;

import com.wandeyun.wuyi.website.bean.Admin;
import com.wandeyun.wuyi.website.controller.AdminController;
import com.wandeyun.wuyi.website.exception.LoginException;
import com.wandeyun.wuyi.website.utils.CookieUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Aspect
@Component
@Slf4j
public class WebSiteAuthorizeAspect {

    //定义切入点loginFailure
    @Pointcut("execution(public * com.wandeyun.wuyi.website.controller.*.*(..))"+
            "&& !execution(public * com.wandeyun.wuyi.website.controller.*.find*(..))"+
            "&& !execution(public * com.wandeyun.wuyi.website.controller.UserController.insert(..))"+
            "&& !execution(public * com.wandeyun.wuyi.website.controller.AdminController.log*(..))"
    )
    public void loginAuthorize() {}

    //前置拦截
    @Before("loginAuthorize()")
    public void doLoginAuthorize() throws Exception {
        System.out.println("拦截成功!");
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        System.out.println("URL >> "+request.getRequestURL().toString());
        //查询cookie
        Cookie cookie = CookieUtil.get(request, "token");
        if (cookie == null) {
            log.warn("【登录校验】Cookie中查不到token");
            throw new LoginException(1003,"您尚未登录，请先登录！");
        }
    }
}
