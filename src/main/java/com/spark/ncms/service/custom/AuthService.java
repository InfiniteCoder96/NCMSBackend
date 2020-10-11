package com.spark.ncms.service.custom;

import com.spark.ncms.payload.AuthRequest;

public interface AuthService extends SuperSevice{
    String authenticateUser(AuthRequest authRequest);
    Boolean registerUser(AuthRequest authRequest);
}
