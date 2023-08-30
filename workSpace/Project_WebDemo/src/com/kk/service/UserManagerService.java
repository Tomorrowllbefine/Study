package com.kk.service;

import com.kk.pojo.User;

import java.util.List;

public interface UserManagerService {
    // 新增用户
    public void addUser(User user);

    // 查询用户
    public List<User> selectUser(User user);

    // 预更新用户查询
    public User selectUserById(int id);

    // 更新用户
    public void modifyUser(User user);
    // 删除用户
    public void delUserById(int id);
}


