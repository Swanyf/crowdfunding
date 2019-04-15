package com.swan.crowdfunding.component.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.swan.crowdfunding.constant.AttrName;
import com.swan.crowdfunding.constant.ErrorMessage;
import com.swan.crowdfunding.entity.UserDO;

public class LoginInterceptor extends HandlerInterceptorAdapter{

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        
        HttpSession session = request.getSession();
        // 获取登录的用户
        UserDO user = (UserDO) session.getAttribute(AttrName.USER_LOGIN_SUCCESS_SESSION);
        
        // 如果user为空，说明没有登录，跳转到登录页面
        if (user == null) {
            // 设置提示信息
            request.setAttribute(AttrName.MESSAGE, ErrorMessage.PLEASE_LOGIN);
            // 跳转到登录页面
            request.getRequestDispatcher("/WEB-INF/portal/login.jsp").forward(request, response);
            return false;
        }
        
        // 已经登录就放行
        return true;
    }
    
}
