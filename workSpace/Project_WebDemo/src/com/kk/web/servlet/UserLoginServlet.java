package com.kk.web.servlet;

import com.kk.commons.Constants;
import com.kk.exception.UserNotFoundException;
import com.kk.pojo.User;
import com.kk.service.UserLoginService;
import com.kk.service.UserLoginServiceImpl;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login.do")
public class UserLoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("username");
        String userPwd = req.getParameter("userpwd");
        String code = req.getParameter("code"); // 验证码

        try{
            HttpSession session = req.getSession();
            String realCode = (String) session.getAttribute(Constants.VALIDATE_CODE_KEY);

            // 输入的验证码正确
            if( realCode.equalsIgnoreCase(code)){
                UserLoginService us = new UserLoginServiceImpl();
                User user = us.UserLogin(userName, userPwd);

                // 创建客户端和服务器的会话连接
                session.setAttribute(Constants.USER_SESSION_KEY, user);

                // 实现用户只能在一处登录
                ServletContext servletContext = this.getServletContext();
                HttpSession temp = (HttpSession) servletContext.getAttribute(user.getId()+"");
                // 判断用户是否已经登录，是则删除旧的登录记录
                if(temp != null){
                    servletContext.removeAttribute(user.getId()+"");
                    temp.invalidate(); // 使原先的会话失效
                }
                servletContext.setAttribute(user.getId()+"", session); // 将当前会话加入全局中

                // 登录成功后，重定向跳转主页面
                resp.sendRedirect("main.jsp");

            }else{ //输入验证码有误
                req.setAttribute(Constants.REQUEST_MSG,"验证码有误，请重新输入！");
                req.getRequestDispatcher("login.jsp").forward(req,resp);
            }

        }catch (UserNotFoundException e){
            req.setAttribute("msg", e.getMessage()); // 传递错误讯息
            req.getRequestDispatcher("login.jsp").forward(req,resp); // 请求转发
        }catch (Exception e){
            e.printStackTrace();
            resp.sendRedirect("error.jsp");
        }
    }
}
