package com.spark.ncms.repository;

import com.spark.ncms.repository.custom.impl.AuthRepoImpl;
import com.spark.ncms.repository.custom.impl.DoctorRepoImpl;
import com.spark.ncms.repository.custom.impl.HospitalRepoImpl;
import com.spark.ncms.repository.custom.impl.PatientRepoImpl;

public class RepoFactory {
    private static RepoFactory repoFactory;

    private RepoFactory(){

    }
    public static RepoFactory getInstance(){
        if (null==repoFactory){
            repoFactory= new RepoFactory();
        }
        return repoFactory;
    }
//Factory
    public <T> T getRepo(RepoType type){
        switch (type){
            case PATIENT:
                return (T)new PatientRepoImpl();
            case HOSPITAL:
                return (T)new HospitalRepoImpl();
            case DOCTOR:
                return (T)new DoctorRepoImpl();
            case USER:
                return (T)new AuthRepoImpl();
            default:
                return null;
        }
    }
}
