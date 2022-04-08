package com.service.impl;

import com.dao.UserDao;
import com.dao.impl.UserDaoImpl;
import com.entity.User;
import com.service.UserService;
import com.utils.Constants;
import com.utils.EmailUtils;
import com.utils.MD5Utils;

import java.sql.SQLException;

public class UserServiceImpl implements UserService {

    public boolean checkedUser(String username) {

        // 1. 创建Dao访问对象
        UserDao userDao = new UserDaoImpl();
        // 2. 执行结果
        try {
            User user = userDao.selectUserByUname(username);
            if (user != null){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // 3. 处理返回值

        return false;
    }

    public int registerUser(User user) throws SQLException {

        // 1. 用户保存到数据库
        UserDao userDao = new UserDaoImpl();
        int row = userDao.insertUser(user);

        // 2. 发送一封邮件
        EmailUtils.sendEmail(user);

        return row;
    }

    public int activeUser(String code) throws SQLException {
        UserDao userDao = new UserDaoImpl();
        // 1. 根据激活码查找用户
        User user = userDao.selectUserByCode(code);
        if (user == null){
            return Constants.ACTIVE_FAIL; // 0激活失败
        }
        // 2. 判断用户是否激活
        if (user.getStatus().equals(Constants.USER_ACTIVE)){
            return Constants.ACTIVE_ALREADY;
        }
        // 3. 进行激活操作
        int i = userDao.updateStatusByUid(user.getUid());
        if (i>0){
            return Constants.ACTIVE_SUCCESS;
        }
        return Constants.ACTIVE_FAIL;
    }

    public User login(String username, String password) throws SQLException {
        //1. 需要密码用md5处理
        String md5password = MD5Utils.md5(password);

        //2. 根据用户名查找用户
        UserDao userDao = new UserDaoImpl();
        User user = userDao.selectUserByUname(username);

        if (user != null && user.getPassword().equals(md5password)){
            return user;
        }

        return null;
    }
}
