package com.smart.dao;

import com.smart.domain.LoginLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * @author maybe
 * @title: LoginLogDao
 * @projectName chapter2
 * @description: 用户登录信息的数据处理
 * @date 19/06/01 上午 11:17
 */
@Repository
public class LoginLogDao {
    private JdbcTemplate jdbcTemplate;
    private final static String INSERT_LOGIN_LOG_SQL="insert into t_login_log(login_log_id, " +
            "user_id, ip, login_datatime) VALUES (?,?,?)";

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate =jdbcTemplate;
    }

    public void insertLoginLog(LoginLog loginLog) {
        Object[] args = {loginLog.getUserId(),loginLog.getUserIp(),loginLog.getLoginDate()};
        jdbcTemplate.update(INSERT_LOGIN_LOG_SQL,args);
    }
}
