package com.spark.ncms.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spark.ncms.entity.Gender;
import com.spark.ncms.entity.Patient;
import com.spark.ncms.entity.SeverityLevel;
import com.spark.ncms.payload.StandardResponse;
import com.spark.ncms.service.ServiceFactory;
import com.spark.ncms.service.ServiceType;
import com.spark.ncms.service.custom.PatientService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;

@WebServlet(urlPatterns = "/api/v1/patient/*")
public class PatientController extends HttpServlet {

    private PatientService patientService;

    public PatientController() {
        patientService = ServiceFactory.getInstance().getService(ServiceType.PATIENT);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String Command = request.getParameter("command");

            switch(Command){
                case "REGISTER_PATIENT":
                    registerPatient(request,response);
                    break;
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String Command = request.getParameter("command");

            switch(Command){
                case "GET_PATIENT":
                    getPatient(request,response);
                    break;
                case "GET_ALL_ACTIVE_PATIENTS":
                    getAllActivePatients(request,response);
                    break;
                default:
                    getAllActivePatients(request,response);
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String Command = request.getParameter("command");

            switch(Command){
                case "ADMIT_PATIENT":
                    admitPatient(request,response);
                    break;
                case "DISCHARGE_PATIENT":
                    dischargePatient(request,response);
                    break;
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    private void getAllActivePatients(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String responseString = mapper.writeValueAsString(new StandardResponse(200, "Patient registered successfully", patientService.getAllPatients()));
        CommonMethods.responseProcess(response, responseString);
    }

    private void getPatient(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String patientIdOrSerialNo = request.getParameter("patientId");
        ObjectMapper mapper = new ObjectMapper();
        String responseString = mapper.writeValueAsString(new StandardResponse(200, "Patient registered successfully", patientService.getPatient(patientIdOrSerialNo)));
        CommonMethods.responseProcess(response, responseString);
    }

    private void registerPatient(HttpServletRequest request, HttpServletResponse response) {
        String responseJson = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            String patientFName = request.getParameter("patientFName");
            String patientLName = request.getParameter("patientLName");
            String district = request.getParameter("district");
            String locationX = request.getParameter("locationX");
            String locationY = request.getParameter("locationY");
            String severityLevel = request.getParameter("severitylevel");
            String gender = request.getParameter("gender");
            String contact = request.getParameter("contact");
            String email = request.getParameter("email");
            String age = request.getParameter("age");

            Patient patient = new Patient();
            patient.setFirstName(patientFName);
            patient.setLastName(patientLName);
            patient.setDistrict(district);
            patient.setLocationX(Integer.parseInt(locationX));
            patient.setLocationY(Integer.parseInt(locationY));
            patient.setSeverityLevel(SeverityLevel.valueOf(severityLevel));
            patient.setGender(Gender.valueOf(gender));
            patient.setContact(contact);
            patient.setEmail(email);
            patient.setAge(Integer.parseInt(age));

            boolean status = patientService.registerPatient(patient);

            String resp;

            if(status){
                responseJson = mapper.writeValueAsString(new StandardResponse(200, "Patient registered successfully", patient));
            }
            else{
                responseJson = mapper.writeValueAsString(new StandardResponse(404, "Something went wrong", ""));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        CommonMethods.responseProcess(response, responseJson);
    }

    private void admitPatient(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String responseJson = null;
        ObjectMapper mapper = new ObjectMapper();
        try{
            String patientId = request.getParameter("patient_id");
            String severityLevel = request.getParameter("severity_level");
            String admitDateStr = request.getParameter("admit_date");
            String admittedBy = request.getParameter("admitted_by");

            java.util.Date _admitDate = new SimpleDateFormat("yyyy-MM-dd").parse(admitDateStr);
            java.sql.Date admitDate = new java.sql.Date(_admitDate.getTime());

            Patient patient = patientService.getPatient(patientId);

            patient.setSeverityLevel(SeverityLevel.valueOf(severityLevel));
            patient.setAdmitDate(admitDate);
            patient.setAdmittedBy(Integer.parseInt(admittedBy));

            boolean status = patientService.admitPatient(patient);

            if(status){
                responseJson = mapper.writeValueAsString(new StandardResponse(200, "Patient admitted successfully", patient));
            }
            else{
                responseJson = mapper.writeValueAsString(new StandardResponse(404, "Something went wrong", ""));
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        CommonMethods.responseProcess(response, responseJson);
    }

    private void dischargePatient(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String responseJson = null;
        ObjectMapper mapper = new ObjectMapper();
        try{
            String patientId = request.getParameter("patient_id");
            String dischargeDateStr = request.getParameter("discharge_date");
            String dischargedBy = request.getParameter("discharged_by");

            java.util.Date _dischargeDate = new SimpleDateFormat("yyyy-MM-dd").parse(dischargeDateStr);
            java.sql.Date dischargeDate = new java.sql.Date(_dischargeDate.getTime());

            Patient patient = patientService.getPatient(patientId);

            patient.setSeverityLevel(SeverityLevel.RECOVERED);
            patient.setDischargeDate(dischargeDate);
            patient.setDischargedBy(Integer.parseInt(dischargedBy));

            boolean status = patientService.dischargePatient(patient);


            if(status){
                responseJson = mapper.writeValueAsString(new StandardResponse(200, "Patient discharged successfully", patient));
            }
            else{
                responseJson = mapper.writeValueAsString(new StandardResponse(404, "Something went wrong", ""));
            }
        }
        catch (Exception e){

        }
        CommonMethods.responseProcess(response, responseJson);
    }
}
