package com.service;

import com.entity.PageBean;
import com.entity.Product;

import java.sql.SQLException;

public interface ProductService {
    PageBean<Product> findPage(String tid, int page, int pageSize) throws SQLException;

    Product findProductById(String pid) throws SQLException;
}
