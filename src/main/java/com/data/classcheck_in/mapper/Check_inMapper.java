package com.data.classcheck_in.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.data.classcheck_in.model.Checkin;
import com.data.classcheck_in.model.Display;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Check_inMapper extends BaseMapper<Checkin> {

    @Select("<script>select * from checkin as c inner join student as s on c.student_id=s.student_id" +
            " where 1=1" +
            "<if test=\"grade!=null and ''!=grade\"> and s.grade=#{grade}</if>" +
            "<if test=\"college!=null and ''!=college\"> and s.college=#{college}</if>" +
            "<if test=\"clazz!=null and ''!=clazz\"> and s.clazz=#{clazz}</if>" +
            "<if test=\"address!=null and ''!=address\"> and c.address=#{address}</if>" +
            "<if test=\"studentId!=null and ''!=studentId\"> and s.student_id like concat('%',#{studentId},'%')</if>" +
            "<if test=\"datetime!=null and ''!=datetime\"> and c.datetime=#{datetime}</if>" +
            "<if test=\"subject!=null and ''!=subject\"> and c.subject=#{subject}</if>" +
            "<if test=\"type!=null and ''!=type\"> and c.type=#{type}</if>" +
            "</script>")
    List<Display> selectSome(Display displayList);

//    @Select("<script>select c.*,s.student_name,s.grade,s.college,s.clazz from checkin as c inner join student as s on s.student_id in " +
//            "<foreach collection=\"ids\" item=\"id\" index=\"index\" open=\"(\" separator=\",\" close=\")\">" +
//            "#{id}" +
//            "</foreach>" +
//            " and s.student_id=c.student_id</script>")
//    List<Display> displayList(@Param("ids") Long[] ids);

    @Select("select c.*,s.student_name,s.grade,s.college,s.clazz from checkin as c inner join student as s on" +
            " s.grade=#{grade} and s.college=#{college} and s.clazz=#{clazz}" +
            " and s.student_id=c.student_id")
    List<Display> displayList(@Param("grade") String grade,@Param("college")String college,@Param("clazz")String clazz);

    @Select("select c.*,s.student_name,s.grade,s.college,s.clazz from checkin as c inner join student as s on s.student_id=c.student_id")
    List<Display> allDisplay();
}
