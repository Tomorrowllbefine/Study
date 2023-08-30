package com.kk.service;

import com.kk.dao.UserLoginDao;
import com.kk.dao.UserLoginIDaompl;
import com.kk.exception.UserNotFoundException;
import com.kk.pojo.User;

public class UserLoginServiceImpl implements UserLoginService{
    /**
     * 用户登录业务
     * @param userName
     * @param userPwd
     * @return
     */
    @Override
    public User UserLogin(String userName, String userPwd) {
        UserLoginDao ud = new UserLoginIDaompl();
        User res = ud.selectByNameAndPwd(userName, userPwd);
        if(res == null){
            throw new UserNotFoundException("用户名或密码有误！");
        }else {
            return res;
        }
    }
}
