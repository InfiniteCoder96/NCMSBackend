package com.spark.ncms.repository.custom.impl;

import com.spark.ncms.entity.Hospital;
import com.spark.ncms.repository.custom.HospitalRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class HospitalRepoImpl implements HospitalRepository {


    @Override
    public boolean addNewHospital(Hospital hospital, Connection con) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Hospital getHospital(String id, Connection con) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean checkHospitalBedsTableEmpty() {
        return false;
    }

    @Override
    public Map<Integer, int[]> getAllHospitalCoordinates() {
        return null;
    }

    @Override
    public List<String> getAvailableHospitals() {
        return null;
    }

    @Override
    public Map<Integer, int[]> getHospitalCoordinates(String id) {
        return null;
    }
}
