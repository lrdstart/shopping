package com.shopping.interceptors;

import com.shopping.model.Users;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    //在请求处理之前进行调用 Controller方法调用之前
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        Users user = (Users) request.getSession().getAttribute("manager");
        if (null == user) {
            // 未登录，重定向到登录页
            System.out.println("已经进行拦截了。。。。。。。。");
            response.sendRedirect("/user/login");
            return false;
        }
        System.out.println("当前用户已登录，登录的用户名为： " + user.getUserName());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object
            handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception ex) throws Exception {
    }
}
