package com.spark.ncms.service.custom;


import com.spark.ncms.entity.Patient;

import java.util.List;
import java.util.Map;

public interface PatientService extends SuperSevice {
    List<Patient> getAllPatients();
    String generatePatientSerialNo();
    Patient getPatient(String patientId);
    int getNearestAvailableHospital(int patientLocX, int patientLocY);
    int getNearestHospital(Map<Integer, int[]> coordinates, int patientLocX, int patientLocY);
    boolean admitPatient(Patient patient);
    boolean registerPatient(Patient patient);
    boolean dischargePatient(Patient patient);
}
