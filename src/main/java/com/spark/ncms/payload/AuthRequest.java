package com.spark.ncms.payload;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AuthRequest {
    private String username;
    private String password;
    private String name;
    private String role;
}
