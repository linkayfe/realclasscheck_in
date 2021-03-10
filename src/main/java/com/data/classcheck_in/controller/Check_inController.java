package com.data.classcheck_in.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.data.classcheck_in.model.Checkin;
import com.data.classcheck_in.model.Display;
import com.data.classcheck_in.model.Student;
import com.data.classcheck_in.service.Check_inService;
import com.data.classcheck_in.service.StudentService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFDataFormat;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.List;

@Controller
@RequestMapping("/check/check")
@SessionAttributes(names = {"displayList"})
public class Check_inController {

    @Autowired
    private Check_inService service;
    @Autowired
    private StudentService studentService;

    @GetMapping
    public String check_inList(Model model,@RequestAttribute("studentId") Long studentId){
        //通过登录的学号获取所在年级学院班级查询考勤表里的信息
        Student student = studentService.getById(studentId);
        if (student!=null) {
            //调用方法查询在该年级学院班级的展示信息
            List<Display> displayList = service.displayList(student.getGrade(),student.getCollege(),student.getClazz());
            model.addAttribute("oldDisplay",new Display());
            model.addAttribute("displayList",displayList);
        }else {
            //进入else说明是大管理员（辅导员等）
            List<Display> displays = service.displayList();
            model.addAttribute("oldDisplay",new Display());
            model.addAttribute("displayList",displays);
        }
        return "main";
    }



    @GetMapping("/selectSome")
    public String selectSome(Display display,Model model) {
        List<Display> displayList = service.selectSome(display);
        model.addAttribute("oldDisplay",display);
        model.addAttribute("displayList",displayList);
        return "main";
    }

//    @GetMapping("/orderBy")
//    @ResponseBody
//    public String orderBy(String column,String type){
//        List<Checkin> check_ins = service.list();
//        return "main";
//    }

    @GetMapping("/toInsert")
    public String toInsert(){
        return "post";
    }

    @PostMapping
    @Transactional
    public String insert(Checkin checkin){
        try{
            service.save(checkin);
        }catch (Exception e){
            return "redirect:/check/check/mistake";
        }
        return "redirect:/check/check";
    }

    @GetMapping("/toUpdate/{checkinNo}")
    public String toUpdate(@PathVariable("checkinNo") Long checkinNo,Model model){
        Display display = service.selectOne(checkinNo);
        model.addAttribute("display",display);
        return "put";
    }

    @PutMapping
    @Transactional
    public String update(Checkin checkin){
        LambdaQueryWrapper<Checkin> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Checkin::getCheckinNo,checkin.getCheckinNo());
        try {
            service.update(checkin, wrapper);
        }catch (Exception e){
            return "redirect:/check/check/mistake";
        }
        return "redirect:/check/check";
    }

    @DeleteMapping("/{checkinNo}")
    @Transactional
    public String delete(@PathVariable("checkinNo")Long checkinNo){
        try{
            service.removeById(checkinNo);
        }catch (Exception e){
            return "redirect:/check/check/mistake";
        }
        return "redirect:/check/check";
    }

    @GetMapping("/mistake")
    public String mistake(){
        return "mistake";
    }

    @RequestMapping("/export")
    public void export(HttpServletResponse response, HttpSession session){
        List<Display> displayList = (List<Display>)session.getAttribute("displayList");
        //将数据写入excel对象，再形成excel文件
        XSSFWorkbook book = new XSSFWorkbook();
        Sheet sheet = book.createSheet();
        sheet.setColumnWidth(1,15*256);

        //设计样式
        CellStyle style = book.createCellStyle();
        Font font = book.createFont();
        font.setBold(true);
        style.setFont(font);


        //开始在jvm版excel中写入数据
        //使用块只是为了不跟后面的内容重名
        {
            Row row = sheet.createRow(0);
            Cell c1 = row.createCell(0);
            Cell c2 = row.createCell(1);
            Cell c3 = row.createCell(2);
            Cell c4 = row.createCell(3);
            Cell c5 = row.createCell(4);
            Cell c6 = row.createCell(5);
            Cell c7 = row.createCell(6);
            Cell c8 = row.createCell(7);
            Cell c9 = row.createCell(8);
            Cell c10 = row.createCell(9);
            c1.setCellValue("考勤号");
            c2.setCellValue("学号");
            c3.setCellValue("日期");
            c4.setCellValue("姓名");
            c5.setCellValue("年级");
            c6.setCellValue("学院");
            c7.setCellValue("班级");
            c8.setCellValue("教室");
            c9.setCellValue("节数");
            c10.setCellValue("性质");
            c1.setCellStyle(style);
            c2.setCellStyle(style);
            c3.setCellStyle(style);
            c4.setCellStyle(style);
            c5.setCellStyle(style);
            c6.setCellStyle(style);
            c7.setCellStyle(style);
            c8.setCellStyle(style);
            c9.setCellStyle(style);
            c10.setCellStyle(style);

        }

        int i = 1;
        for (Display display : displayList){
            Row row = sheet.createRow(i++);
            Cell c1 = row.createCell(0);
            Cell c2 = row.createCell(1);
            Cell c3 = row.createCell(2);
            Cell c4 = row.createCell(3);
            Cell c5 = row.createCell(4);
            Cell c6 = row.createCell(5);
            Cell c7 = row.createCell(6);
            Cell c8 = row.createCell(7);
            Cell c9 = row.createCell(8);
            Cell c10 = row.createCell(9);
            XSSFCellStyle cellStyle2 = book.createCellStyle();
            XSSFDataFormat format = book.createDataFormat();
            cellStyle2.setDataFormat(format.getFormat("0"));
            c2.setCellStyle(cellStyle2);
            c1.setCellValue(display.getCheckinNo());
            c2.setCellValue(display.getStudentId());
            c3.setCellValue(display.getDatetime());
            c4.setCellValue(display.getStudentName());
            c5.setCellValue(display.getGrade());
            c6.setCellValue(display.getCollege());
            c7.setCellValue(display.getClazz());
            c8.setCellValue(display.getAddress());
            c9.setCellValue(display.getSubject());
            c10.setCellValue(display.getType());

        }

        //将jvm中的excel数据写入指定的位置中
        String path = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        File file = new File(path,"考勤表.xlsx");

        OutputStream os = null;
        InputStream fis = null;
        OutputStream fos = null;

        try {
            os = new FileOutputStream(file);
            book.write(os);

            //将装有用户数据的excel文件下载到浏览器
            fis = new FileInputStream(file);
            fos = response.getOutputStream();

            response.setHeader("content-disposition","attachment;filename=check_in.xlsx");
            while(true){
                int b = fis.read();
                if (b==-1){
                    break;
                }
                fos.write(b);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (os!=null)
                    os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (fis!=null)
                    fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (fos!=null)
                    fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
