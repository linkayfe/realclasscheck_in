package com.data.classcheck_in.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.data.classcheck_in.mapper.StudentMapper;
import com.data.classcheck_in.model.Student;
import com.data.classcheck_in.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {

    @Autowired
    private StudentMapper mapper;

    public Long[] studentIds(Student student){
        return mapper.studentIds(student);
    }
}
