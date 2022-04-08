package com.service.impl;

import com.dao.TypeDao;
import com.dao.impl.TypeDaoImpl;
import com.entity.Type;
import com.service.TypeService;

import java.sql.SQLException;
import java.util.List;

public class TypeServiceImpl implements TypeService {
    @Override
    public List<Type> findAll() throws SQLException {
        TypeDao typeDao = new TypeDaoImpl();
        List<Type> types = typeDao.selectAll();
        return types;
    }
}
