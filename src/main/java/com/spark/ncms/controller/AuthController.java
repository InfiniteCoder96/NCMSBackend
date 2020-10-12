package com.spark.ncms.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.spark.ncms.constants.ResponseCode;
import com.spark.ncms.payload.AuthRequest;
import com.spark.ncms.payload.AuthResponse;
import com.spark.ncms.response.StandardResponse;
import com.spark.ncms.service.ServiceFactory;
import com.spark.ncms.service.ServiceType;
import com.spark.ncms.service.custom.AuthService;
import com.spark.ncms.security.JWTUtil;

import javax.json.JsonObject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(urlPatterns = "/api/v1/auth/login")
public class AuthController extends HttpServlet {

    private AuthService authService;
    private JWTUtil jwtUtil = new JWTUtil();

    public AuthController() {
        authService = ServiceFactory.getInstance().getService(ServiceType.USER);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            String Command = request.getParameter("command");

            switch(Command){
                case "REGISTER":
                    registerUser(request,response);
                    break;
                case "LOGIN":
                    loginUser(request,response);
                    break;
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }


    }

    private void loginUser(HttpServletRequest request, HttpServletResponse resp) throws IOException {
        JsonObject jsonObject = CommonMethods.getJsonObject(request);
        ObjectMapper mapper = new ObjectMapper();

        AuthRequest authRequest = new AuthRequest();

        authRequest.setUsername(jsonObject.getString("username"));
        authRequest.setPassword(jsonObject.getString("password"));

        try {

                String role = authService.authenticateUser(authRequest);

                if (!role.equals("Invalid user credentials")) {
                    String token = jwtUtil.createToken(authRequest.getUsername());
                    AuthResponse authResponse = new AuthResponse(authRequest.getUsername(), role);
                    String responseJson = mapper.writeValueAsString(new StandardResponse(ResponseCode.SUCCESS, token, authResponse));
                    CommonMethods.responseProcess(resp, responseJson);
                } else {
                    String responseJson = mapper.writeValueAsString(new StandardResponse(ResponseCode.UNAUTHORIZED, "These credentials are invalid", null));
                    resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "These credentials are invalid");
                    CommonMethods.responseProcess(resp, responseJson);
                }


        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
            return;


        }
    }

    private void registerUser(HttpServletRequest request, HttpServletResponse resp) throws IOException {
        JsonObject jsonObject = CommonMethods.getJsonObject(request);
        ObjectMapper mapper = new ObjectMapper();
        String responseJson;
        AuthRequest authRequest = new AuthRequest();

        authRequest.setUsername(jsonObject.getString("username"));
        authRequest.setPassword(jsonObject.getString("password"));
        authRequest.setName(jsonObject.getString("name"));
        String role = jsonObject.getString("role");

        if(role.equals("")){
            authRequest.setRole("user");
        }
        else{
            authRequest.setRole(jsonObject.getString("role"));
        }

        try {

            if (authService.registerUser(authRequest)) {
                String token = jwtUtil.createToken(authRequest.getUsername());
                AuthResponse authResponse = new AuthResponse(authRequest.getUsername(), role);
                responseJson = mapper.writeValueAsString(new StandardResponse(ResponseCode.SUCCESS, token, authResponse));
                CommonMethods.responseProcess(resp, responseJson);
            } else {
                responseJson = mapper.writeValueAsString(new StandardResponse(ResponseCode.UNAUTHORIZED, "These credentials are invalid", null));
            }

        } catch (Exception e) {
            e.printStackTrace();
            responseJson = mapper.writeValueAsString(new StandardResponse(400, "Username already registered", null));
            return;

        }
        CommonMethods.responseProcess(resp, responseJson);
    }


}
