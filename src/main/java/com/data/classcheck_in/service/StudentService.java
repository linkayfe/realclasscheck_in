package com.data.classcheck_in.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.data.classcheck_in.model.Student;


public interface StudentService extends IService<Student> {

    Long[] studentIds(Student student);
}
