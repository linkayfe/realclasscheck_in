package com.data.classcheck_in.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.data.classcheck_in.model.Student;

import java.util.List;


public interface StudentService extends IService<Student> {

    List<Student> studentIds(Student student);

}
