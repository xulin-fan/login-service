package dao;

import pojo.User;
import utils.EncryptionUtils;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by barryfan on 6/10/19.
 */
public class UserDao {

    private static final Map<String, User> userTable
            =  new HashMap<String,User>();

    static {
        userTable.put("jjj",new User("jjj", EncryptionUtils.encrypPassword("123456")));
        userTable.put("xxx",new User("xxx",EncryptionUtils.encrypPassword("123456")));
    }

    public User getUser(String userName){
        return userTable.get(userName);
    }

    public boolean insertUser(User user){
        userTable.put(user.getUsername(), user);
        return true;
    }

    public boolean deleteUser(User user){
        userTable.remove(user.getUsername());
        return true;
    }

    public boolean modifyUser(User user){
        return insertUser(user);
    }
 }
