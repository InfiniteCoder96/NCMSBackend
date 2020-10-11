package com.spark.ncms.repository.custom.impl;

import com.spark.ncms.controller.db.DBConnectionPool;
import com.spark.ncms.entity.Patient;
import com.spark.ncms.helper.Helper;
import com.spark.ncms.payload.AuthRequest;
import com.spark.ncms.repository.custom.AuthRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthRepoImpl implements AuthRepository {

    @Override
    public String authenticateUser(AuthRequest authRequest) {
        ResultSet rs = null;
        Connection con = null;
        PreparedStatement stmt = null;
        Patient patient = null;

        String userRole;

        try{
            con = DBConnectionPool.getInstance().getConnection();

            stmt = con.prepareStatement(Helper.CHECK_USER);
            stmt.setString(1, authRequest.getUsername());
            stmt.setString(2, authRequest.getPassword());

            rs = stmt.executeQuery();

            if(rs.next()){
                return rs.getString("role");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally
        {
            DBConnectionPool.getInstance().close(stmt);
            DBConnectionPool.getInstance().close(con);
        }
        return "Invalid user credentials";
    }

    @Override
    public Boolean registerUser(AuthRequest authRequest) {
        Connection con = null;
        PreparedStatement stmt = null;
        try
        {
            con = DBConnectionPool.getInstance().getConnection();
            stmt = con.prepareStatement(Helper.REGISTER_NEW_USER);
            stmt.setString(1, authRequest.getUsername());
            stmt.setString(2, authRequest.getPassword());
            stmt.setString(3, authRequest.getName());
            stmt.setString(4, authRequest.getRole());

            int changedRows = stmt.executeUpdate();
            return (changedRows == 1);
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return false;
        }
        finally
        {
            DBConnectionPool.getInstance().close(stmt);
            DBConnectionPool.getInstance().close(con);
        }
    }
}
