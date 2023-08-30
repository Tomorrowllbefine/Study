package com.kk.dao;

import com.kk.pojo.User;

import java.util.List;

public interface UserManagerDao {

    public void addUser(User user);
    public List<User> selectUserByProperty( User user);
    public User selectUserById(int id);
    void updateUserById(User user);
    void delUserById(int id);
}
