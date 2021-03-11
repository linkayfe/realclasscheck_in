package com.data.classcheck_in.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.data.classcheck_in.model.Consumer;
import com.data.classcheck_in.model.Student;
import com.data.classcheck_in.service.ConsumerService;
import com.data.classcheck_in.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/check")
public class ConsumerController {

    @Autowired
    private ConsumerService service;
    @Autowired
    private StudentService studentService;

    @RequestMapping
    public String toIndex(){
        return "index";
    }

    @RequestMapping("/login")
    public ModelAndView login(HttpSession session, Consumer consumer){
        ModelAndView model = new ModelAndView();
        LambdaQueryWrapper<Consumer> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Consumer::getStudentId,consumer.getStudentId());
        Consumer selectConsumer = service.getOne(wrapper);
        if (selectConsumer !=null && consumer.getPassword().equals(selectConsumer.getPassword())){
            session.setAttribute("login", selectConsumer);
            session.setMaxInactiveInterval(30*60);
            model.setViewName("redirect:/check/main");
            return model;
        }else {
            model.addObject("param","用户名或密码错误");
            model.setViewName("redirect:/check");
            return model;
        }
    }

    @RequestMapping("/main")
    public ModelAndView main(HttpSession session, ModelAndView mv){
        Consumer consumer = (Consumer)session.getAttribute("login");
        Long studentId = consumer.getStudentId();
        mv.addObject("studentId",studentId);
        mv.setViewName("forward:/check/check");
        return mv;
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.removeAttribute("login");
        session.invalidate();
        return "redirect:/check";
    }

    @RequestMapping("/toInsert")
    public String toInsert(){
        return "register";
    }

    @RequestMapping("/insert")
    @Transactional
    public String insert(Consumer consumer, Model model){
        try {
            Student student = studentService.getById(consumer.getStudentId());
            String position = student.getPosition();
            if ("班长".equals(position)||"纪律委员".equals(position)||"学习委员".equals(position)){
                consumer.setManager(1);
            }
            boolean flag = service.save(consumer);
            if (!flag){
                model.addAttribute("mistake","该学号已被注册");
                return "mistake";
            }
        }catch (Exception e){
            model.addAttribute("mistake","系统繁忙");
            return "mistake";
        }
        model.addAttribute("consumer",consumer);
        return "forward:/check/login";
    }

    @RequestMapping("/checkStuId")
    @ResponseBody
    public Student checkStudentId(Long studentId){
        Student student = studentService.getById(studentId);
        if (student==null){
            student = new Student();
            student.setStudentId(0L);
        }
        return student;
    }
}
