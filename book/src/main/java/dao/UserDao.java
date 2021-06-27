package dao;

import pojo.User;

/**
 * @Author Rookie
 * @Date 2021/6/20
 */
public interface UserDao {
    public User queryForName(String name);
    public User queryForNameAndPassword(String name, String password);
    public int saveUser(User user);
}
