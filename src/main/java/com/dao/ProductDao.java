package com.dao;

import com.entity.Product;

import java.sql.SQLException;
import java.util.List;

public interface ProductDao {
    long selectCountById(String tid) throws SQLException;
    List<Product> selectProductByPage(int page, int pageSize, String tid) throws SQLException;

    Product selectProductByPid(String pid) throws SQLException;
}
