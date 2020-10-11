package com.spark.ncms.test;

import com.spark.ncms.service.custom.impl.DoctorServiceImpl;
import org.testng.annotations.Test;

import java.sql.SQLException;

public class DoctorServiceImplTest {

    DoctorServiceImpl doctorService;

    DoctorServiceImplTest(){
        this.doctorService=new DoctorServiceImpl();
    }

    @Test
    public void testMocking() throws SQLException, ClassNotFoundException {
        /*Connection connection = mock(Connection.class);
        Assert.assertNotNull(doctorService.getHospitalBedList("D001", connection ));*/
    }
}
