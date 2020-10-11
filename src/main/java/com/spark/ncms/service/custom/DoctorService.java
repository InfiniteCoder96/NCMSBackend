package com.spark.ncms.service.custom;


import com.spark.ncms.entity.Doctor;

public interface DoctorService extends SuperSevice{
    boolean addNewDoctor(Doctor newDoctor);
}
