package com.spark.ncms.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spark.ncms.entity.Hospital;
import com.spark.ncms.response.StandardResponse;
import com.spark.ncms.service.ServiceFactory;
import com.spark.ncms.service.ServiceType;
import com.spark.ncms.service.custom.HospitalService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;

@WebServlet(urlPatterns = "/api/v1/hospitals")
public class HospitalController extends HttpServlet {

    private HospitalService hospitalService;

    public HospitalController() {
        hospitalService = ServiceFactory.getInstance().getService(ServiceType.HOSPITAL);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String Command = request.getParameter("command");

            switch(Command){
                case "REGISTER_HOSPITAL":
                    addNewHospital(request,response);
                    break;
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String Command = request.getParameter("command");

            switch(Command){
                case "GET_HOSPITAL":
                    getHospital(request,response);
                    break;
                case "GET_ALL_HOSPITALS":
                    getAllHospitals(request,response);
                    break;
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    private void getAllHospitals(HttpServletRequest request, HttpServletResponse response) {
    }

    private void getHospital(HttpServletRequest request, HttpServletResponse response) {
    }


    private void addNewHospital(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String responseJson = null;
        try{
            String hospitalName = request.getParameter("hospital_name");
            String district = request.getParameter("district");
            String locationX = request.getParameter("locationX");
            String locationY = request.getParameter("locationY");
            String buildDate = request.getParameter("build_date");

            java.util.Date _buildDate = new SimpleDateFormat("yyyy-MM-dd").parse(buildDate);
            java.sql.Date buildDate_ = new java.sql.Date(_buildDate.getTime());

            Hospital newHospital = new Hospital();

            newHospital.setName(hospitalName);
            newHospital.setDistrict(district);
            newHospital.setLocationX(Integer.parseInt(locationX));
            newHospital.setLocationY(Integer.parseInt(locationY));
            newHospital.setBuildDate(buildDate_);

            boolean status = hospitalService.addNewHospital(newHospital);


            if(status){
                responseJson = mapper.writeValueAsString(new StandardResponse(200, "Hospital added successfully", newHospital));
            }
            else{
                responseJson = mapper.writeValueAsString(new StandardResponse(404, "Something went wrong", ""));
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        CommonMethods.responseProcess(response, responseJson);
    }
}
