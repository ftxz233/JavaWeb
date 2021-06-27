package dao.impl;

import dao.UserDao;
import pojo.User;

/**
 * @Author Rookie
 * @Date 2021/6/20
 */
public class UserDaoImpl extends BaseDao implements UserDao {
    public User queryForName(String name) {
        String sql = "select id,username,password,email from t_user where username = ?";
        return queryForOne(User.class,sql,name);
    }

    public User queryForNameAndPassword(String name, String password) {
        String sql = "select id,username,password,email from t_user where username = ? and password = ?";
        return queryForOne(User.class,sql,name,password);
    }

    public int saveUser(User user) {
        String sql = "insert into t_user(username,password,email) values(?,?,?)";
        return update(sql,user.getUsername(),user.getPassword(),user.getEmail());
    }
    public Object count(int count){
        String sql = "select count(*) from t_user where id<?";
        return queryForSingleValue(sql,count);
    }
}
