package com.smart.service;

import com.smart.dao.LoginLogDao;
import com.smart.dao.UserDao;
import com.smart.domain.LoginLog;
import com.smart.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author chenyamcc
 * @title: UserService
 * @projectName chapter2
 * @description: 用户的操作类
 * @date 19/06/01 上午 11:37
 */
@Service
public class UserService {
    private LoginLogDao loginLogDao;
    private UserDao userDao;

    @Autowired
    public void setUserDao(LoginLogDao loginLogDao) {
        this.loginLogDao = loginLogDao;
    }

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public boolean hasMatchUser(String userName, String passWord) {
        int matchCount = userDao.getMatchCount(userName, passWord);
        return matchCount > 0;
    }

    public User findUserByUserName(String userName) {
        return userDao.findUserByUserName(userName);
    }
    /**
    * 功能描述:将用户的登录数据添加进日志,并给用户的论坛币增加5个.如果出现异常就回滚
     * @param user 登录成功后的用户
    * @Author: chenyam
    * @Date: 19/06/01 上午 11:50
    */
    @Transactional(rollbackFor = Exception.class)
    public void loginSuccess(User user) {
        user.setCredits(5 + user.getCredits());
        LoginLog loginLog = new LoginLog();
        loginLog.setUserId(user.getUserId());
        loginLog.setUserIp(user.getLastIp());
        loginLog.setLoginDate(user.getLastVisit());
        userDao.updateLoginInfo(user);
        loginLogDao.insertLoginLog(loginLog);
    }
}
