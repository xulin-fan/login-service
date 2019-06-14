package services;

import dao.UserDao;
import pojo.User;

/**
 * Created by barryfan on 6/10/19.
 */
public class LoginService {

    private final UserDao userDao = new UserDao();

    public boolean login(User user) {
        User result = userDao.getUser(user.getUsername());
        return result != null && result.getPassword().equals(user.getPassword());
    }
}
