package com.spark.ncms.repository.custom;

import com.spark.ncms.entity.Hospital;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface HospitalRepository extends SuperRepository {
    boolean addNewHospital(Hospital hospital, Connection con ) throws SQLException, ClassNotFoundException;
    Hospital getHospital(String id , Connection con) throws SQLException, ClassNotFoundException;
    boolean checkHospitalBedsTableEmpty();
    Map<Integer,int[]> getAllHospitalCoordinates();
    List<String> getAvailableHospitals();
    Map<Integer,int[]> getHospitalCoordinates(String id);
}
