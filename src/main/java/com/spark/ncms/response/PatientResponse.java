package com.spark.ncms.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientResponse {
    private String serialNo;
    private int bedNo;
    private String hospitalName;
    private int queueNo;
}
