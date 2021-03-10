package com.data.classcheck_in.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.data.classcheck_in.model.Student;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentMapper extends BaseMapper<Student> {

    @Select("select student_id from student where grade=#{grade} and college=#{college} and clazz=#{clazz}")
    Long[] studentIds(Student student);
}
