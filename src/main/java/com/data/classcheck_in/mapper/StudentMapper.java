package com.data.classcheck_in.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.data.classcheck_in.model.Student;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentMapper extends BaseMapper<Student> {

    @Select("select * from student where grade=#{grade} and college=#{college} and clazz=#{clazz}")
    List<Student> studentIds(Student student);
}
