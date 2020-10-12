package com.spark.ncms.service;

import com.spark.ncms.controller.StatisticsController;
import com.spark.ncms.service.custom.impl.*;

public class ServiceFactory {
    public static ServiceFactory serviceFactory;

    private ServiceFactory(){

    }
    //Singleton
    public static ServiceFactory getInstance() {
        if(serviceFactory==null){
            serviceFactory=new ServiceFactory();
        }
        return serviceFactory;
    }

    //Generic Method
    public <T> T getService(ServiceType type){
        switch (type){
            case PATIENT:
                return (T)new PatientServiceImpl();
            case DOCTOR:
                return (T)new DoctorServiceImpl();
            case MOH:
                return (T)new MohServiceImpl();
            case USER:
                return (T)new AuthServiceImpl();
            case HOSPITAL:
                return (T)new HospitalServiceImpl();
            case STATS:
                return (T)new StatisticsController();
                default:
                    return null;
        }
    }

}
