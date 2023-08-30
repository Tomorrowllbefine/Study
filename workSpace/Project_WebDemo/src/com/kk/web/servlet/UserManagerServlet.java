package com.kk.web.servlet;

import com.kk.pojo.User;
import com.kk.service.UserManagerService;
import com.kk.service.UserManagerServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/userManager.do")
public class UserManagerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 由于有多个业务，因此需要对传入的flag进行判断以选择
        String flag = req.getParameter("flag");
        if( "addUser".equals(flag)){
            this.addUser(req,resp);
        } else if ("selectUser".equals(flag)) {
            this.selectUser(req,resp);
        }else if("preUpdate".equals(flag)){
            this.preUpdate(req,resp);
        }else if("modifyUser".equals(flag)){
            this.modifyUser(req,resp);
        }else {
            this.delUser(req,resp);
        }
    }

    /**
     * 实现新增用户
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void addUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

        // 获取用户数据
        User user = this.getUser(req);
        // 由于Servlet是最后一层，需要加上try-catch来包裹处理所有异常
        try{
            UserManagerService ums = new UserManagerServiceImpl();
            ums.addUser(user);
            resp.sendRedirect("ok.jsp");
        }catch (Exception e){
            e.printStackTrace();
            resp.sendRedirect("error.jsp");
        }

    }

    /**
     * 查询用户
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void selectUser(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        // 获取用户数据
        User user = this.getUser(request);

        try{
            UserManagerService ums = new UserManagerServiceImpl();
            List<User> list = ums.selectUser(user);
            request.setAttribute("list",list);
            request.getRequestDispatcher("userManager/viewUser.jsp").forward(request,response);
        }catch (Exception e){
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }

    /**
     * 预更新查询
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void preUpdate(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException{
        // 获取用户数据
        String id = req.getParameter("userid");
        try{
            UserManagerService ums = new UserManagerServiceImpl();
            User user = ums.selectUserById(Integer.parseInt(id));
            req.setAttribute("user",user);
            req.getRequestDispatcher("userManager/updateUser.jsp").forward(req,resp);
        }catch(Exception e){
            e.printStackTrace();
            resp.sendRedirect("error.jsp");
        }
    }

    /**
     * 更新用户
     * @param req
     * @param resp
     * @throws IOException
     * @throws ServletException
     */
    private void modifyUser(HttpServletRequest req, HttpServletResponse resp) throws IOException,ServletException{
        // 获取用户数据
        User user = this.getUser(req);
        String id = req.getParameter("userid");
        user.setId(Integer.parseInt(id)); // 将前台传过来的id获取到并添入user对象中作为参数传入
        try{
            UserManagerService ums = new UserManagerServiceImpl();
            ums.modifyUser(user);
            resp.sendRedirect("ok.jsp");
        }catch(Exception e){
            e.printStackTrace();
            resp.sendRedirect("error.jsp");
        }
    }

    /**
     * 删除用户
     * @param req
     * @param resp
     * @throws IOException
     * @throws ServletException
     */
    private void delUser(HttpServletRequest req, HttpServletResponse resp)throws IOException,ServletException{
        String id = req.getParameter("userid");
        try{
            UserManagerService ums = new UserManagerServiceImpl();
            ums.delUserById(Integer.parseInt(id));
            resp.sendRedirect("ok.jsp");
        }catch (Exception e){
            e.printStackTrace();
            resp.sendRedirect("error.jsp");
        }
    }


    /**
     * 内部封装用于获取request中的user信息并返回user对象
     * @param req
     * @return
     */
    private User getUser(HttpServletRequest req){

        String name = req.getParameter("username");
        String pwd = req.getParameter("userpwd");
        String sex = req.getParameter("usersex");
        String phoneNum = req.getParameter("phonenumber");
        String qqNum = req.getParameter("qqnumber");
        User user = new User(name, pwd, sex, phoneNum, qqNum);
        return user;

    }
}
