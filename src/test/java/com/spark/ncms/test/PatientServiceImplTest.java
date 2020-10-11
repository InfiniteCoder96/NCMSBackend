package com.spark.ncms.test;

import com.spark.ncms.service.custom.impl.PatientServiceImpl;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PatientServiceImplTest {

    PatientServiceImpl patientService;

    PatientServiceImplTest(){
        this.patientService=new PatientServiceImpl();
    }

    @Test(dependsOnMethods = "testTimeOut")
    public void testPatientId(){
        Assert.assertNotNull(patientService.generatePatientSerialNo());
    }

    @Test(timeOut = 100)
    public void testTimeOut(){
        patientService.generatePatientSerialNo();
    }

    @Test
    public void testNearestHospital(){
        Assert.assertEquals(patientService.getNearestAvailableHospital(100, 200), 101.9803902718557);
    }
}
