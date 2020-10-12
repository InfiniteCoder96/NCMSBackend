package com.spark.ncms.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.spark.ncms.entity.Doctor;
import com.spark.ncms.payload.StandardResponse;
import com.spark.ncms.service.ServiceFactory;
import com.spark.ncms.service.ServiceType;
import com.spark.ncms.service.custom.DoctorService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/api/v1/doctor")
public class DoctorController extends HttpServlet {

    private DoctorService doctorService;

    public DoctorController() {
        doctorService = ServiceFactory.getInstance().getService(ServiceType.DOCTOR);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String Command = request.getParameter("command");

            switch(Command){
                case "REGISTER_DOCTOR":
                    addNewDoctor(request,response);
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
                case "GET_DOCTOR":
                    getDoctor(request,response);
                    break;
                case "GET_ALL_DOCTORS":
                    getAllDoctors(request,response);
                    break;
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    private void getDoctor(HttpServletRequest request, HttpServletResponse response) {
    }

    private void getAllDoctors(HttpServletRequest request, HttpServletResponse response) {
    }

    private void addNewDoctor(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String resp;
        ObjectMapper mapper = new ObjectMapper();
        String responseJson = null;
        try{
            String doctorName = request.getParameter("doctor_name");
            String hospitalId = request.getParameter("hospital_id");
            String isDoctor = request.getParameter("is_director");

            Doctor newDoctor = new Doctor();

            newDoctor.setName(doctorName);
            newDoctor.setHospitalId(hospitalId);
            newDoctor.setIsDirector(Integer.parseInt(isDoctor));

            boolean status = doctorService.addNewDoctor(newDoctor);

            if(status){
                responseJson = mapper.writeValueAsString(new StandardResponse(200, "Doctor added successfully", ""));
            }
            else{
                responseJson = mapper.writeValueAsString(new StandardResponse(404, "Something went wrong", ""));
            }
        }
        catch(Exception e){
            resp = "Something went wrong";
            e.printStackTrace();
        }
        CommonMethods.responseProcess(response, responseJson);
    }

}
