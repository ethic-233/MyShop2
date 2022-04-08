package com.dao.impl;

import com.dao.UserDao;
import com.entity.User;
import com.utils.C3P0Utils;
import com.utils.Constants;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

/*
* 数据库访问实现类
*
* */
public class UserDaoImpl implements UserDao {

    @Override
    public User selectUserByUname(String username) throws SQLException {
        // 1. 创建一个QueryRunner对象
        QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
        // 2. 执行sql语句
        String sql = "SELECT u_id AS uid, u_name AS username, u_password AS upassword" +
                ", u_sex AS usex, u_status AS ustatus, u_code AS code, u_email AS email" +
                ", u_role AS urole FROM user WHERE u_name = ?";
        // 看到没，queryRunner的性能非常差
        User user = queryRunner.query(sql, new BeanHandler<User>(User.class), username);
        return user;
    }

    @Override
    public int insertUser(User user) throws SQLException {
        // 1. 创建一个QueryRunner对象
        QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
        // 2. 插入数据
        String sql = "INSERT INTO user(u_name, u_password, u_sex, u_status," +
                "u_code, u_email, u_role) VALUES (?,?,?,?,?,?,?)";
        int rows = queryRunner.update(sql, user.getUsername(), user.getPassword(), user.getSex(),
        user.getStatus(), user.getCode(), user.getEmail(), user.getRole());
        return rows;
    }

    @Override
    public User selectUserByCode(String code) throws SQLException {
        // 1. 创建一个QueryRunner对象
        QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
        // 2. 执行sql语句
        String sql = "SELECT u_id AS uid, u_name AS username, u_password AS upassword" +
                ", u_sex AS usex, u_status AS ustatus, u_code AS code, u_email AS email" +
                ", u_role AS urole FROM user WHERE u_code = ?";
        User user = queryRunner.query(sql, new BeanHandler<User>(User.class), code);
        return user;
    }

    @Override
    public int updateStatusByUid(int uid) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
        String sql = "UPDATE user SET u_status = ? WHERE u_id = ?";
        int row = queryRunner.update(sql, Constants.USER_ACTIVE, uid);
        return row;
    }
}
