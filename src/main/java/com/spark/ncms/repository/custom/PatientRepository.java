package com.spark.ncms.repository.custom;

import com.spark.ncms.entity.Patient;

import java.util.List;
import java.util.Map;

public interface PatientRepository extends SuperRepository {
    boolean registerPatient(Patient patient);
    String generatePatientSerialNo();
    Patient getPatient(String patientId);
    boolean dischargePatient(Patient patient);
    List<Patient> getAllPatients();
    int getNearestAvailableHospital(int patientLocX, int patientLocY);
    int getNearestHospital(Map<Integer, int[]> coordinates, int patientLocX, int patientLocY);
    boolean admitPatient(Patient patient);
}
