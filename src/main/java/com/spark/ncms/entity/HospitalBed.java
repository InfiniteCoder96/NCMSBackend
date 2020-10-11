package com.spark.ncms.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter  @NoArgsConstructor
public class HospitalBed {
    private String id;
    private String hospitalId;
    private String patientId;
}
