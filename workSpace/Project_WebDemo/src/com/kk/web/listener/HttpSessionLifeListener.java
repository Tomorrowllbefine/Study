package com.kk.web.listener;

import com.kk.commons.Constants;
import com.kk.pojo.User;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * 处理引session超时被销毁时发生的异常
 */
public class HttpSessionLifeListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        HttpSessionListener.super.sessionCreated(se);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        ServletContext servletContext = session.getServletContext(); // 获取全局容器

        User user = (User) session.getAttribute(Constants.USER_SESSION_KEY); // 获取用户信息
        servletContext.removeAttribute(user.getId() + ""); // 删除全局容器中对应的键值对
        System.out.println("HttpSessionListener->sessionDestroyed() 已调用servletContext.removeAttribute()");
    }
}
