package com.spark.ncms.service.custom.impl;

import com.spark.ncms.entity.Patient;
import com.spark.ncms.repository.RepoFactory;
import com.spark.ncms.repository.RepoType;
import com.spark.ncms.repository.custom.HospitalBedRepository;
import com.spark.ncms.repository.custom.HospitalRepository;
import com.spark.ncms.repository.custom.PatientRepository;
import com.spark.ncms.service.custom.PatientService;

import java.util.List;
import java.util.Map;

public class PatientServiceImpl implements PatientService {

    private HospitalRepository hospitalRepo;
    private HospitalBedRepository hospitalBedRepo;
    private PatientRepository patientRepo;
    private RepoFactory repoFactory;

    public PatientServiceImpl() {
        this.repoFactory=RepoFactory.getInstance();
        hospitalRepo = repoFactory.getRepo(RepoType.HOSPITAL);
        hospitalBedRepo = repoFactory.getRepo(RepoType.HOSPITAL_BED);
        patientRepo = repoFactory.getRepo(RepoType.PATIENT);
    }

    @Override
    public boolean registerPatient(Patient patient) {
        return patientRepo.registerPatient(patient);
    }

    @Override
    public String generatePatientSerialNo() {
        return patientRepo.generatePatientSerialNo();
    }

    @Override
    public Patient getPatient(String patientId) {
        return patientRepo.getPatient(patientId);
    }

    @Override
    public boolean dischargePatient(Patient patient) {
        return patientRepo.dischargePatient(patient);
    }

    @Override
    public List<Patient> getAllPatients() {
        return patientRepo.getAllPatients();
    }

    @Override
    public int getNearestAvailableHospital(int patientLocX, int patientLocY){
        return patientRepo.getNearestAvailableHospital(patientLocX, patientLocY);
    }

    @Override
    public int getNearestHospital(Map<Integer, int[]> coordinates, int patientLocX, int patientLocY){
        return patientRepo.getNearestHospital(coordinates, patientLocX, patientLocY);
    }

    @Override
    public boolean admitPatient(Patient patient) {
        return patientRepo.admitPatient(patient);
    }


}
