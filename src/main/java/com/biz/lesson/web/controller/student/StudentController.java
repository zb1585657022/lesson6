package com.biz.lesson.web.controller.student;

import com.biz.lesson.business.stu.GradeManager;
import com.biz.lesson.business.stu.StuManager;
import com.biz.lesson.business.stu.SubjectManager;
import com.biz.lesson.exception.BusinessAsserts;
import com.biz.lesson.model.student.Grade;
import com.biz.lesson.model.student.Student;
import com.biz.lesson.model.student.Subject;
import com.biz.lesson.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/student/student")

public class StudentController extends BaseController {

    @Autowired
    private StuManager stuManager;

    @Autowired
    private GradeManager gradeManager;

    @Autowired
    private SubjectManager subjectManager;

    @RequestMapping("/list")
    public ModelAndView list(HttpServletRequest request) throws Exception{
        ModelAndView modelAndView = new ModelAndView("student/student/list");
        List<Student> students = stuManager.listEnableStudents();
        request.setAttribute("stuList" ,students);
        modelAndView.addObject("systemProperties",students);
        return modelAndView;
    }

    @RequestMapping("/add")
    public ModelAndView add(HttpServletRequest request) throws Exception {
        ModelAndView modelAndView = new ModelAndView("student/student/add");
        modelAndView.addObject("cmd", "add");
        modelAndView.addObject("roles", stuManager.listEnableStudents());
        List<Grade> grades = gradeManager.listEnableGrades();
        request.setAttribute("grades",grades);

        Student student = new Student();
        modelAndView.addObject("student", student);

        addReferer(request);
        return modelAndView;
    }

    @RequestMapping("/save_add")
    public ModelAndView save_add(Student vo, String pwd, BindingResult result, HttpServletRequest request) throws Exception {
        Student student = new Student();
        student.setStuId(Integer.parseInt(request.getParameter("stuId")));
        student.setStuName(request.getParameter("stuName"));
        student.setStuSex(request.getParameter("stuSex"));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new java.sql.Date(sdf.parse(request.getParameter("stuBirthday")).getTime());
        student.setStuBirthday(date);
        student.setStuGrade(request.getParameter("stuGrade"));
        student.setGrade(gradeManager.findByName(request.getParameter("stuGrade")));
        stuManager.createStudent(student);
        return referer(request);
    }

    @RequestMapping("/edit")
    public ModelAndView edit(Integer stuId, HttpServletRequest request) throws Exception {
        ModelAndView modelAndView = new ModelAndView("student/student/add");
        List<Grade> grades = gradeManager.listEnableGrades();
        request.setAttribute("grades",grades);
        Student student = stuManager.getStudnet(stuId);
        BusinessAsserts.exists(student, stuId);

        modelAndView.addObject("student", student);
        modelAndView.addObject("cmd", "edit");
        modelAndView.addObject("roles", stuManager.listAllRoles());
        addReferer(request);
        return modelAndView;
    }

    @RequestMapping("/save_edit")
    public ModelAndView save_edit(Student student, String pwd, BindingResult result, HttpServletRequest request) throws Exception {
        student.setStuId(Integer.parseInt(request.getParameter("stuId")));
        student.setStuName(request.getParameter("stuName"));
        student.setStuSex(request.getParameter("stuSex"));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new java.sql.Date(sdf.parse(request.getParameter("stuBirthday")).getTime());
        student.setStuBirthday(date);
        student.setStuGrade(request.getParameter("stuGrade"));
        student.setGrade(gradeManager.findByName(request.getParameter("stuGrade")));
        BusinessAsserts.exists(student, student.getStuId());
        stuManager.updateStudent(student);
        return referer(request);
    }

    @RequestMapping("/stuId/delete")
    public ModelAndView save_delete(@RequestParam("stuId") Integer stuId ,HttpServletRequest request) throws Exception{
        Student student = stuManager.getStudnet(stuId);
        Boolean b = null;
        try {
            stuManager.deleteStudent(student);
            b =  true;
        } catch (Exception e) {
            b =  false;
        }
        return referer(request);
    }


}
