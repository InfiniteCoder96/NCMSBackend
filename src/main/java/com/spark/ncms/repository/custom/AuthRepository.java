package com.spark.ncms.repository.custom;

import com.spark.ncms.payload.AuthRequest;

public interface AuthRepository extends SuperRepository{
    String authenticateUser(AuthRequest authRequest);
    Boolean registerUser(AuthRequest authRequest);
}
