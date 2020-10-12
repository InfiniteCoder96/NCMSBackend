package com.spark.ncms.controller;

import com.spark.ncms.service.ServiceFactory;
import com.spark.ncms.service.ServiceType;
import com.spark.ncms.service.custom.StatisticsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/api/v1/stats")
public class StatisticsController extends HttpServlet {

    private StatisticsService statisticsService;
    public StatisticsController() {
        statisticsService = ServiceFactory.getInstance().getService(ServiceType.USER);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String Command = request.getParameter("command");

            switch(Command){
                case "GENSUMSTATS":
                    getGeneralInitialStats(request,response);
                    break;
                case "HOSPSUMSTATS":
                    getHospitalSummaryStats(request,response);
                    break;
                case "MOHSUMSTATS":
                    getMoHSummaryStats(request,response);
                    break;
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    private void getHospitalSummaryStats(HttpServletRequest request, HttpServletResponse response) {

    }

    private void getMoHSummaryStats(HttpServletRequest request, HttpServletResponse response) {
    }

    private void getGeneralInitialStats(HttpServletRequest request, HttpServletResponse response) {
    }
}
