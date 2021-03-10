package com.data.classcheck_in.controller;

import com.data.classcheck_in.model.Student;
import com.data.classcheck_in.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;


@Controller
@RequestMapping("/check/student")
public class StudentController {

    @Autowired
    private StudentService service;

    @GetMapping
    @ResponseBody
    public List<Student> studentList(Long studentId){
        Student student = service.getById(studentId);
        List<Student> students = null;
        if (student!=null) {
            //不是大管理员
            List<Student> studentList = service.studentIds(student);
            Set<Student> set = new TreeSet<>(Comparator.comparing(o -> (o.getGrade() + "" + o.getCollege() + "" + o.getClazz())));
            set.addAll(studentList);
            students = new ArrayList<>(set);
        }else {
            //辅导员
            List<Student> studentList = service.list();
            Set<Student> set = new TreeSet<>(Comparator.comparing(o -> (o.getGrade() + "" + o.getCollege() + "" + o.getClazz())));
            set.addAll(studentList);
            students = new ArrayList<>(set);
        }
        return students;
    }
}
