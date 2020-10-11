package com.spark.ncms.service.custom.impl;

import com.spark.ncms.repository.RepoFactory;
import com.spark.ncms.repository.RepoType;
import com.spark.ncms.repository.custom.HospitalBedRepository;
import com.spark.ncms.repository.custom.HospitalRepository;
import com.spark.ncms.service.custom.MohService;

public class MohServiceImpl implements MohService {

    HospitalRepository hospitalRepo;
    HospitalBedRepository hospitalBedRepository;

    public MohServiceImpl(){
        hospitalRepo = RepoFactory.getInstance().getRepo(RepoType.HOSPITAL);
        hospitalBedRepository = RepoFactory.getInstance().getRepo(RepoType.HOSPITAL_BED);
    }


}
