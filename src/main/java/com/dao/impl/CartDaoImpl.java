package com.dao.impl;

import com.dao.CartDao;
import com.entity.Cart;
import com.entity.Product;
import com.utils.C3P0Utils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CartDaoImpl implements CartDao {

    @Override
    public Cart hasCart(int uid, String pid) throws SQLException, InvocationTargetException, IllegalAccessException {
        QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
        String sql = "SELECT p.p_name AS pname, p.p_id AS pid, p.t_id AS tid," +
                "p.ptime AS ptime, p.p_image AS pimage, p_state AS pstate," +
                "p.p_info AS pinfo, p.p_price AS pprice, c.c_id AS cid, c.u_id AS uid, c.c_count AS ccount" +
                "c.c_num AS cnum FROM product p JOIN cart c ON p.p_id = c.p_id WHERE" +
                "c.u_id = ? AND c.p_id = ?;";
        Map<String, Object> query = queryRunner.query(sql, new MapHandler(), uid, pid);

        if (query == null){
            return null;
        }

        Cart cart = new Cart();
        Product product = new Product();
        BeanUtils.populate(cart, query);
        BeanUtils.populate(product, query);
        cart.setProduct(product);
        return cart;
    }

    @Override
    public void updateCart(Cart cart) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
        String sql = "UPDATE cart SET c_num = ?, c_count = ? WHERE c_id = ?";
        queryRunner.update(sql, cart.getCnum(), cart.getCcount(), cart.getCid());
    }

    @Override
    public void insertCart(Cart cart) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
        String sql = "INSERT INTO cart (u_id, p_id, c_num, c_count) VALUE (?, ?, ?, ?)";
        queryRunner.update(sql, cart.getUid(), cart.getProduct().getPid(), cart.getCnum(), cart.getCcount());
    }

    @Override
    public List<Cart> selectCartsByUid(int uid) throws SQLException, InvocationTargetException, IllegalAccessException {
        // 查询cart需要关联到商品表
        QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
        String sql = "SELECT p.p_name AS pname, p.p_id AS pid, p.t_id AS tid," +
                "p.ptime AS ptime, p.p_image AS pimage, p_state AS pstate," +
                "p.p_info AS pinfo, p.p_price AS pprice, c.c_id AS cid, c.u_id AS uid, c.c_count AS ccount" +
                "c.c_num AS cnum FROM product p JOIN cart c ON p.p_id = c.p_id WHERE" +
                "c.u_id = ? AND c.p_id = ?;";
        List<Map<String, Object>> list = queryRunner.query(sql, new MapListHandler(), uid);

        if (list == null){
            return null;
        }

        List<Cart> carts = new ArrayList<>();

        for (Map<String, Object> map : list) {
            // cart + product
            Cart cart = new Cart();
            Product product = new Product();

            BeanUtils.populate(cart, map);
            BeanUtils.populate(product, map);

            cart.setProduct(product);
            carts.add(cart);
        }

        return carts;
    }

    @Override
    public void deleteCartByCid(String cid) throws SQLException {

        QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());

        String sql = "DELETE FROM cart WHERE c_id = ?;";

        queryRunner.update(sql, cid);
    }

    @Override
    public void updateByCid(BigDecimal count, BigDecimal cnumbig, String cid) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
        String sql = "UPDATE cart SET c_count = ?, c_num = ? WHERE c_id = ?;";
        queryRunner.update(sql, count, cnumbig, cid);
    }

    @Override
    public void deleteCartByUid(String uid) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
        String sql = "DELETE FROM cart WHERE u_id = ?;";
        queryRunner.update(sql, uid);
    }
}
