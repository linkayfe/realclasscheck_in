package com.data.classcheck_in.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.data.classcheck_in.mapper.ConsumerMapper;
import com.data.classcheck_in.model.Consumer;
import com.data.classcheck_in.service.ConsumerService;
import org.springframework.stereotype.Service;

@Service
public class ConsumerServiceImpl
        extends ServiceImpl<ConsumerMapper, Consumer>
        implements ConsumerService {
}
