package com.data.classcheck_in.model;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Student implements Serializable {
    @TableId
    private Long studentId;
    private String studentName;
    private String sex;
    private Integer age;
    private String grade;
    private String college;
    private String clazz;
    private String position;
}
