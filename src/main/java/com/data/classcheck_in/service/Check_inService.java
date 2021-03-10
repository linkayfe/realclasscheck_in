package com.data.classcheck_in.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.data.classcheck_in.model.Checkin;
import com.data.classcheck_in.model.Display;

import java.util.List;

public interface Check_inService extends IService<Checkin> {

    List<Display> selectSome(Display displayList);
    Display selectOne(Long checkinNo);
    List<Display> displayList(String grade,String college,String clazz);
    List<Display> displayList();
}
