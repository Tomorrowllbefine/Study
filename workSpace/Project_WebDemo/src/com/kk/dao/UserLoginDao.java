package com.kk.dao;

import com.kk.pojo.User;

public interface UserLoginDao {
    public User selectByNameAndPwd(String userName, String userPwd);
}
