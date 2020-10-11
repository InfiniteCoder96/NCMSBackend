package com.spark.ncms.service.custom.impl;

import com.spark.ncms.payload.AuthRequest;
import com.spark.ncms.repository.RepoFactory;
import com.spark.ncms.repository.RepoType;
import com.spark.ncms.repository.custom.AuthRepository;
import com.spark.ncms.service.custom.AuthService;

public class AuthServiceImpl implements AuthService {

    private AuthRepository authRepository;

    public AuthServiceImpl(){
        authRepository= RepoFactory.getInstance().getRepo(RepoType.USER);
    }


    @Override
    public String authenticateUser(AuthRequest authRequest) {
        return authRepository.authenticateUser(authRequest);
    }

    @Override
    public Boolean registerUser(AuthRequest authRequest) {
        return authRepository.registerUser(authRequest);
    }
}
