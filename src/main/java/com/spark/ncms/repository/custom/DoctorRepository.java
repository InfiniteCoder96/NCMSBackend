package com.spark.ncms.repository.custom;


import com.spark.ncms.entity.Doctor;

import java.sql.Connection;
import java.sql.SQLException;

public interface DoctorRepository extends SuperRepository{
    String getHospitalId(String doctorId, Connection con) throws SQLException, ClassNotFoundException;
    boolean addNewDoctor(Doctor newDoctor);
}
