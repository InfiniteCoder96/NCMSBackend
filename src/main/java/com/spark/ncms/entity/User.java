package com.spark.ncms.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter  @NoArgsConstructor
public class User {
    private String username;
    private String password;
    private String name;
    private String role;
}
