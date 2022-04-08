package com.dao;

import com.entity.Type;

import java.sql.SQLException;
import java.util.List;

public interface TypeDao {

    List<Type> selectAll() throws SQLException;
}
