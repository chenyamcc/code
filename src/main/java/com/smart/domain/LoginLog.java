package com.smart.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * @author maybe
 * @title: LoginLog
 * @projectName chapter2
 * @description: TODO
 * @date 19/06/01 上午 1:07
 */
public class LoginLog implements Serializable {
    private int loginLogId;
    private int userId;
    private String userIp;
    private Date loginDate;

    public int getLoginLogId() {
        return loginLogId;
    }

    public void setLoginLogId(int loginLogId) {
        this.loginLogId = loginLogId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserIp() {
        return userIp;
    }

    public void setUserIp(String userIp) {
        this.userIp = userIp;
    }

    public Date getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }
}
