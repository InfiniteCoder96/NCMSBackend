package com.spark.ncms.repository.custom.impl;

import com.spark.ncms.controller.db.DBConnectionPool;
import com.spark.ncms.entity.Doctor;
import com.spark.ncms.helper.Helper;
import com.spark.ncms.repository.custom.DoctorRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DoctorRepoImpl implements DoctorRepository {
    @Override
    public String getHospitalId(String doctorId, Connection con) throws SQLException, ClassNotFoundException {
        PreparedStatement pstm = con.prepareStatement("select hospital_id from doctor where id =?");
        pstm.setObject(1, doctorId);
        ResultSet rst = pstm.executeQuery();
        if(rst.next()){
            return rst.getString(1);
        }

        return null;
    }

    @Override
    public boolean addNewDoctor(Doctor newDoctor) {
        Connection con = null;
        PreparedStatement stmt = null;
        try
        {
            con = DBConnectionPool.getInstance().getConnection();
            stmt = con.prepareStatement(Helper.ADD_NEW_DOCTOR);
            stmt.setString(1, newDoctor.getName());
            stmt.setString(2, newDoctor.getHospitalId());
            stmt.setInt(3, newDoctor.getIsDirector());

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
