package com.kk.service;

import com.kk.dao.UserManagerDao;
import com.kk.dao.UserManagerDaoImpl;
import com.kk.pojo.User;

import java.util.List;

/**
 * 用户管理业务层
 */
public class UserManagerServiceImpl implements UserManagerService{
    UserManagerDao umd = new UserManagerDaoImpl();

    /**
     * 新增用户业务
     * @param user
     */
    @Override
    public void addUser(User user) {
        umd.addUser(user);
    }

    public List<User> selectUser(User user){
        return umd.selectUserByProperty(user);
    }

    /**
     * 预更新查询业务
     * @param id
     * @return
     */
    @Override
    public User selectUserById(int id) {
        return umd.selectUserById(id);
    }

    /**
     * 更新用户业务
     * @param user
     */
    @Override
    public void modifyUser(User user) {
        umd.updateUserById(user);
    }

    /**
     * 删除用户业务
     * @param id
     */
    @Override
    public void delUserById(int id) {
        umd.delUserById(id);
    }
}
