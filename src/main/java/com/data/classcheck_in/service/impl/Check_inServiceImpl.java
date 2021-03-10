package com.data.classcheck_in.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.data.classcheck_in.mapper.Check_inMapper;
import com.data.classcheck_in.model.Checkin;
import com.data.classcheck_in.model.Display;
import com.data.classcheck_in.service.Check_inService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Check_inServiceImpl
        extends ServiceImpl<Check_inMapper, Checkin>
        implements Check_inService {

    @Autowired
    private Check_inMapper mapper;

    //模糊查询调用
    public List<Display> selectSome(Display displayList){
        System.out.println(displayList);
        return mapper.selectSome(displayList);
    }

    //通过一个id数组查询展示信息
//    public List<Display> displayList(Long[] ids){
//        return mapper.displayList(ids);
//    }

    //通过年级学院班级查询
    @Override
    public List<Display> displayList(String grade, String college, String clazz) {
        return mapper.displayList(grade, college, clazz);
    }

    @Override
    public List<Display> displayList() {
        return mapper.allDisplay();
    }


}
