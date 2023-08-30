package com.kk.dao;

import com.kk.commons.JdbcUtils;
import com.kk.pojo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class UserLoginIDaompl implements UserLoginDao {
    /**
     * 用户登录业务的数据库查询
     * @param userName 用户名
     * @param userPwd 用户密码
     * @return
     */
    public User selectByNameAndPwd(String userName, String userPwd){

        Connection conn = null;
        User user = null;
        try{
            conn = JdbcUtils.gerConnection();
            PreparedStatement pstm = conn.prepareStatement("select * from users where username = ? and userpwd = ?");
            pstm.setString(1,userName);
            pstm.setString(2,userPwd);
            // 执行sql语句
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                user = new User();
                user.setId(rs.getInt("userid"));
                user.setName(rs.getString("username"));
                user.setPwd(rs.getString("userpwd"));
                user.setSex(rs.getString("usersex"));
                user.setPhoneNum(rs.getString("phonenum"));
                user.setQqNum(rs.getString("qqnum"));
            }
        }catch (Exception e ){
            e.printStackTrace();
        }
        // 返回结果
        return user;
    }
}
