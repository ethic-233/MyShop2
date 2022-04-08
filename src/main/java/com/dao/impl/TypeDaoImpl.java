package com.dao.impl;

import com.dao.TypeDao;
import com.entity.Type;
import com.utils.C3P0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class TypeDaoImpl implements TypeDao {
    @Override
    public List<Type> selectAll() throws SQLException {
        QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
        String sql = "SELECT t_id AS tid, t_name AS tname, t_info AS tinfo FROM type LIMIT 5;";
        List<Type> list = queryRunner.query(sql, new BeanListHandler<Type>(Type.class));
        return list;
    }
}
