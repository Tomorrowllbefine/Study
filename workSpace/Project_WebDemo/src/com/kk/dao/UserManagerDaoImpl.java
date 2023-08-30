package com.kk.dao;

import com.kk.commons.JdbcUtils;
import com.kk.pojo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户管理持久层
 */
public class UserManagerDaoImpl implements UserManagerDao{
    /**
     * 新增用户
     * @param user
     */
    @Override
    public void addUser(User user) {
        Connection conn = null;

        try{
            conn = JdbcUtils.gerConnection();
            conn.setAutoCommit(false); // 取消事务的自动提交
            PreparedStatement pstm = conn.prepareStatement("insert  into users values(DEFAULT,?,?,?,?,?)");
            pstm.setString(1,user.getName());
            pstm.setString(2,user.getPwd());
            pstm.setString(3,user.getSex());
            pstm.setString(4,user.getPhoneNum());
            pstm.setString(5,user.getQqNum());
            // 执行sql语句
            pstm.execute();
            // 事务提交
            conn.commit();

        }catch (Exception e){
            e.printStackTrace();
            JdbcUtils.roolbackConnection(conn); // 发生异常事务回滚
        }finally {
            JdbcUtils.closeConnection(conn); // 关闭连接
        }
    }

    /**
     * 查询用户
     * @param user
     * @return
     */
    @Override
    public List<User> selectUserByProperty(User user) {
        Connection conn = null;
        List<User> list = new ArrayList<>();
        try{
            conn = JdbcUtils.gerConnection();
            String sql = this.getSql(user);
            PreparedStatement psmt = conn.prepareStatement(sql);
            ResultSet rs = psmt.executeQuery();
            while(rs.next()){
                User user1 = new User();
                user1.setQqNum(rs.getString("qqnum"));
                user1.setSex(rs.getString("usersex"));
                user1.setName(rs.getString("username"));
                user1.setPwd(rs.getString("userpwd"));
                user1.setId(rs.getInt("userid"));
                user1.setPhoneNum(rs.getString("phonenum"));
                list.add(user1);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            JdbcUtils.closeConnection(conn);
        }
        return list;
    }

    /**
     * 预更新查询
     * @param id
     * @return
     */
    @Override
    public User selectUserById(int id) {

        Connection conn = JdbcUtils.gerConnection();
        User user1 = null;
        try{
            PreparedStatement psmt = conn.prepareStatement("select * from users where userid = ?");
            psmt.setInt(1,id);
            ResultSet rs = psmt.executeQuery();
            while(rs.next()){
                user1 = new User();
                user1.setQqNum(rs.getString("qqnum"));
                user1.setSex(rs.getString("usersex"));
                user1.setName(rs.getString("username"));
                user1.setPwd(rs.getString("userpwd"));
                user1.setId(rs.getInt("userid"));
                user1.setPhoneNum(rs.getString("phonenum"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JdbcUtils.closeConnection(conn);
        }
        return user1;
    }

    /**
     * 用户更新
     * @param user
     */
    @Override
    public void updateUserById(User user) {
        Connection conn = null;
        try{
            conn = JdbcUtils.gerConnection();
            conn.setAutoCommit(false);
            PreparedStatement psmt = conn.prepareStatement("update users set username = ?, usersex = ?, phonenum = ?, qqnum = ? where userid = ?");
            psmt.setString(1,user.getName());
            psmt.setString(2,user.getSex());
            psmt.setString(3,user.getPhoneNum());
            psmt.setString(4,user.getQqNum());
            psmt.setInt(5,user.getId());
            psmt.execute();
            conn.commit();
        }catch (Exception e){
            e.printStackTrace();
            JdbcUtils.roolbackConnection(conn);
        }finally{
            JdbcUtils.closeConnection(conn);
        }
    }

    /**
     * 删除用户
     * @param id
     */
    @Override
    public void delUserById(int id) {
        Connection conn = null;
        try{
            conn = JdbcUtils.gerConnection();
            conn.setAutoCommit(false);
            PreparedStatement psmt = conn.prepareStatement("delete from users where userid = ?");
            psmt.setInt(1,id);
            psmt.execute();
            conn.commit();
        }catch (Exception e){
            e.printStackTrace();
            JdbcUtils.roolbackConnection(conn);
        }finally{
            JdbcUtils.closeConnection(conn);
        }
    }

    /**
     * 内置获取查询Sql语句方法
     * @param user
     * @return
     */
    private String getSql(User user) {
        StringBuilder sb = new StringBuilder("select * from users where 1=1");
        if(user.getName() != null && user.getName().length() >0){
            sb.append(" and username = " + user.getName());
        }
        if (user.getPwd() != null && user.getPwd().length() >0){
            sb.append(" and userpwd = "+ user.getPwd());
        }
        if(user.getSex() != null && user.getSex().length() >0){
            sb.append(" and usersex = " + user.getSex());
        }
        if(user.getPhoneNum() != null && user.getPhoneNum().length() > 0){
            sb.append(" and phonenum = " + user.getPhoneNum());
        }
        if( user.getQqNum() != null && user.getQqNum().length()>0){
            sb.append(" and qqnum = " + user.getQqNum());
        }
        return sb.toString();
    }
}
