package com.smart.dao;

import com.smart.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author maybe
 * @title: UserDao
 * @projectName chapter2
 * @description: 用户类的数据访问层
 * @date 19/06/01 上午 9:18
 */
@Repository
public class UserDao {
    private JdbcTemplate template;
    private final static String MATCH_COUNT_SQL = "SELECT count(*) from t_user where user_name =?" +
            "AND password=?";
    private final static String UPDATE_LOGIN_INFO_SQL ="UPDATE t_user SET last_visit=?,last_ip=?," +
            "credits=? where user_id=?";

    @Autowired
    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }

    public int getMatchCount(String name, String passWord) {
        return template.queryForObject(MATCH_COUNT_SQL, new Object[]{name, passWord},
                Integer.class);
    }

    public User findUserByUserName(final String name) {
        final User user = new User();
        String sqlStr = "SELECT user_id,user_name,credits from t_user where user_name=?";
        template.query(sqlStr, new Object[]{name}, new RowCallbackHandler() {
            public void processRow(ResultSet resultSet) throws SQLException {
                user.setUserId(resultSet.getInt("user_id"));
                user.setUserName(name);
                user.setCredits(resultSet.getInt("credits"));
            }
        });
        return user;
    }
    public void updateLoginInfo(User user) {
        template.update(UPDATE_LOGIN_INFO_SQL, user.getLastVisit(),user.getLastIp(),
                user.getCredits(),user.getUserId());
    }
}
