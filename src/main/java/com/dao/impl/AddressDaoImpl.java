package com.dao.impl;

import com.dao.AddressDao;
import com.entity.Address;
import com.utils.C3P0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class AddressDaoImpl implements AddressDao {

    @Override
    public List<Address> selectAddressByUid(int uid) throws SQLException {

        QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
        String sql = "SELECT a_id AS aid, u_id AS uid, a_name AS aname, a_phone" +
                "AS aphone, a_detail AS adetail, a_state AS astate FROM" +
                "address WHERE u_id = ? ORDER BY a_state DESC;";
        List<Address> list = queryRunner.query(sql, new BeanListHandler<Address>(Address.class), uid);
        return list;
    }

    @Override
    public void insertAddress(Address address) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
        String sql = "INSERT INTO address (u_id, a_name, a_phone, a_detail, a_state) VALUE (?, ?, ?, ?, ?)";
        queryRunner.update(sql, address.getUid(), address.getName(),
                address.getPhone(), address.getDetail(), address.getState());

    }

    @Override
    public void deleteAddressByAid(String aid) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
        String sql = "DELETE FROM address WHERE a_id = ?;";
        queryRunner.update(sql, aid);
    }

    @Override
    public void updateAddressToDefault(String aid) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
        String sql = "UPDATE address SET a_state = 1 WHERE a_id = ?;";
        queryRunner.update(sql, aid);
    }

    @Override
    public void updateAddressToCommons(String aid, int uid) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
        String sql = "UPDATE address SET a_state = 0 WHERE a_id != ? AND uid = ?";
        queryRunner.update(sql, aid, uid);
    }

    @Override
    public void updateAddressByAid(Address address) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
        String sql = "UPDATE address SET a_state = ?, a_name = ?, a_phone = ?, a_detail = ? WHERE a_id = ?";
        queryRunner.update(sql, address.getState(), address.getName(), address.getPhone(), address.getDetail(), address.getAid());
    }
}
