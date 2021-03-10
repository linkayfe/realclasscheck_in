package com.data.classcheck_in.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Display implements Serializable {

    private Long checkinNo;
    private Long studentId;
    private String datetime;
    private String studentName;
    private String grade;
    private String college;
    private String clazz;
    private String address;
    private String subject;
    private String type;
}
