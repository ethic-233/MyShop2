package com.dao.impl;

import com.dao.OrdersDao;
import com.entity.Address;
import com.entity.Item;
import com.entity.Orders;
import com.entity.Product;
import com.utils.C3P0Utils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OrdersDaoImpl implements OrdersDao {

    @Override
    public void insertOrders(Orders orders) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
        String sql = "INSERT INTO orders (o_id, a_id, u_id, o_count, o_state, o_time)" +
                "VALUE(?,?,?,?,?,?);";
        queryRunner.update(sql, orders.getOid(), orders.getAid(), orders.getUid(), orders.getOcount(),
                orders.getOstate(), orders.getOtime());
    }

    @Override
    public void insertItems(List<Item> items) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
        Object [][] params = new Object[items.size()][];
        String sql = "INSERT INTO item (o_id, p_id, i_count, i_num) VALUE(?,?,?,?);";
        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            params[i] = new Object[]{item.getOid(), item.getPid(),
            item.getCount(), item.getInum()};
        }
        queryRunner.batch(sql, params);
    }

    @Override
    public List<Orders> selectOrdersByUid(int uid) throws SQLException, InvocationTargetException, IllegalAccessException {
        QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
        String sql = "SELECT o.o_id AS oid, o.u_id AS uid," +
                "o.a_id AS aid, o.o_count AS ocount, o.o_time AS otime," +
                "o.o_state AS ostate, a.a_name AS aname, a.a_phone AS aphone," +
                "a.a_detail AS adetail, a.a_state AS astate FROM" +
                "address a JOIN orders o ON a.a_id = o.a_id " +
                "WHERE o.u_id = ?;";
        List<Map<String, Object>> list = queryRunner.query(sql, new MapListHandler(), uid);
        if (list == null){
            return null;
        }
        List<Orders> ordersList = new ArrayList<>();
        for (Map<String, Object> map : list) {
            Orders orders = new Orders();
            Address address = new Address();
            BeanUtils.populate(orders, map);
            BeanUtils.populate(address, map);
            orders.setAddress(address);
            ordersList.add(orders);
        }
        return ordersList;
    }

    @Override
    public Orders selectOrdersByOid(String oid) throws SQLException, InvocationTargetException, IllegalAccessException {
        QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
        String sql = "SELECT o.o_id AS oid, o.u_id AS uid," +
                "o.a_id AS aid, o.o_count AS ocount, o.o_time AS otime," +
                "o.o_state AS ostate, a.a_name AS aname, a.a_phone AS aphone," +
                "a.a_detail AS adetail, a.a_state AS astate FROM" +
                "address a JOIN orders o ON a.a_id = o.a_id " +
                "WHERE o.o_id = ?;";
        Map<String, Object> map = queryRunner.query(sql, new MapHandler(), oid);
        if (map == null){
            return null;
        }
        Orders orders = new Orders();
        Address address = new Address();
        BeanUtils.populate(orders, map);
        BeanUtils.populate(address, map);
        orders.setAddress(address);

        return orders;
    }

    @Override
    public List<Item> selectItemsByOid(String oid) throws SQLException, InvocationTargetException, IllegalAccessException {
        QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
        // 订单项和商品
        String sql = "SELECT p.p_id AS pid, p.t_id AS tid, p.p_name AS pname, p.p_time AS ptime, " +
                "p.p_image AS pimage, p.p_state AS pstate, p.p_info AS pinfo, p.p_price AS pprice" +
                "i.o_id AS oid, i.i_id AS iid, i.i_count AS icount, i.i_num AS inum" +
                "FROM product p JOIN item i ON p.p_id = i.p_id WHERE i.o_id = ? ;";
        List<Map<String, Object>> list = queryRunner.query(sql, new MapListHandler(), oid);
        if (list == null){
            return null;
        }
        List<Item> items = new ArrayList<>();
        for (Map<String, Object> map : list) {
            Item item = new Item();
            Product product = new Product();
            BeanUtils.populate(product, map);
            BeanUtils.populate(item, map);
            item.setProduct(product);
            items.add(item);
        }
        return items;
    }

    @Override
    public void updateStateByOid(String oid) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
        String sql = "UPDATE orders SET o_state = ? WHERE o_id = ?;";
        queryRunner.update(sql, 2, oid);
    }
}
