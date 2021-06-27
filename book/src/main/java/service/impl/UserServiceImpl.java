package service.impl;

import dao.UserDao;
import dao.impl.UserDaoImpl;
import pojo.User;
import service.UserService;

/**
 * @Author Rookie
 * @Date 2021/6/20
 */
public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();

    public void regist(User user) {
       userDao.saveUser(user);
    }

    public User login(User user) {
        return userDao.queryForNameAndPassword(user.getUsername(),user.getPassword());
    }

    public boolean existsUserName(String name) {
        if(userDao.queryForName(name)==null){
            return false;
        }
        return true;
    }
}
