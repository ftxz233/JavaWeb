package service;

import pojo.User;

/**
 * @Author Rookie
 * @Date 2021/6/20
 */
public interface UserService {
    public void regist(User user);
    public User login(User user);
    public boolean existsUserName(String name);
}
