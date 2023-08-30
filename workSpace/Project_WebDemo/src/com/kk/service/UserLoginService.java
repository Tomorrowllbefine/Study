package com.kk.service;

import com.kk.pojo.User;

public interface UserLoginService {
    public User UserLogin(String userName, String userPwd);
}
