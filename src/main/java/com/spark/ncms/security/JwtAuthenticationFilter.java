package com.spark.ncms.security;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class JwtAuthenticationFilter implements Filter {

    JwtTokenProvider jwtTokenProvider = new JwtTokenProvider();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String jwt = getJwtFromRequest(request);

        if (!jwtTokenProvider.validateToken(jwt)) {
            //if a request came without a token or without a valid token
            PrintWriter out = response.getWriter();
            response.setContentType("application/json");
            response.setStatus(401);
            out.println("{\"code\":401,\"message\":\"error\",\"data\":null}");
            out.flush();

        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }

    }

    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7, bearerToken.length());
        }
        return null;
    }
}
