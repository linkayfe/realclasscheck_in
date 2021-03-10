package com.data.classcheck_in.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.data.classcheck_in.model.Consumer;
import com.data.classcheck_in.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/check")
@SessionAttributes(names={"login"})
public class ConsumerController {

    @Autowired
    private ConsumerService service;

    @RequestMapping
    public String toIndex(){
        return "index";
    }

    @RequestMapping("/login")
    public String login(HttpSession session, RedirectAttributes model, Consumer consumer){
        LambdaQueryWrapper<Consumer> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Consumer::getStudentId,consumer.getStudentId());
        Consumer selectConsumer = service.getOne(wrapper);
        if (selectConsumer !=null && consumer.getPassword().equals(selectConsumer.getPassword())){
            session.setAttribute("login", selectConsumer);
            session.setMaxInactiveInterval(30*60);
            return "redirect:/check/main";
        }else {
            model.addAttribute("param","用户名或密码错误");
            return "index";
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
    public String logout(HttpSession session){
        session.removeAttribute("login");
        session.invalidate();
        return "redirect:/check";
    }
}
