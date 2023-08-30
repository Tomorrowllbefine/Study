package com.kk.web.filter;


import com.kk.commons.Constants;
import com.kk.pojo.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"*.do","*.jsp"})
public class UserLoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

    /**
     * 实现拦截过滤：
     * 对login.do 以及 login.jsp进行放行；
     * 其他页面需要session中的user是否存在给予放行
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 获取请求
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String uri = request.getRequestURI();

        // 判断当前请求是否为login.jsp或login.do或validateCode.do 是则放行
        if( (uri.indexOf("login.do")!=-1)||uri.indexOf("login.jsp") != -1 || uri.indexOf("validateCode.do") != -1){
            filterChain.doFilter(servletRequest,servletResponse);
        }else {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute(Constants.USER_SESSION_KEY);
            if (user != null){
                filterChain.doFilter(servletRequest,servletResponse);
            }else {
                request.setAttribute(Constants.REQUEST_MSG,"请先登录！");
                request.getRequestDispatcher("login.jsp").forward(servletRequest,servletResponse);
            }
        }
    }
}