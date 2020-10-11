package com.spark.ncms.service.custom.impl;

import com.spark.ncms.entity.Doctor;
import com.spark.ncms.repository.RepoFactory;
import com.spark.ncms.repository.RepoType;
import com.spark.ncms.repository.custom.DoctorRepository;
import com.spark.ncms.repository.custom.HospitalBedRepository;
import com.spark.ncms.repository.custom.HospitalRepository;
import com.spark.ncms.repository.custom.PatientRepository;
import com.spark.ncms.service.custom.DoctorService;

public class DoctorServiceImpl implements DoctorService {

    private DoctorRepository doctorRepo;
    private HospitalBedRepository hospitalBedRepo;
    private PatientRepository patientRepo;
    private HospitalRepository hospitalRepo;

    public DoctorServiceImpl(){
        doctorRepo = RepoFactory.getInstance().getRepo(RepoType.DOCTOR);
        hospitalBedRepo = RepoFactory.getInstance().getRepo(RepoType.HOSPITAL_BED);
        patientRepo = RepoFactory.getInstance().getRepo(RepoType.PATIENT);
        hospitalRepo = RepoFactory.getInstance().getRepo(RepoType.HOSPITAL);
    }

    @Override
    public boolean addNewDoctor(Doctor newDoctor) {
        return doctorRepo.addNewDoctor(newDoctor);
    }


}
