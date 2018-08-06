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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    public ModelAndView save_add(HttpServletRequest request) throws Exception {
        Student student = new Student();
        student.setStuId(Integer.parseInt(request.getParameter("stuId")));
        student.setStuName(request.getParameter("stuName"));
        student.setStuSex(request.getParameter("stuSex"));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new java.sql.Date(sdf.parse(request.getParameter("stuBirthday")).getTime());
        student.setStuBirthday(date);
        student.setStuAveMark(0);
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
        modelAndView.addObject("roles", stuManager.listEnableStudents());
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

    @RequestMapping("/chooseSub")
    public ModelAndView chooseSub(Integer stuId) throws Exception{
        List<Subject> subjects = subjectManager.listEnableSubjects();
        Student student = stuManager.getStudnet(stuId);
        ModelAndView modelAndView = new ModelAndView("student/student/chooseSub");
        modelAndView.addObject("subjects",subjects);
        modelAndView.addObject("student",student);
        return modelAndView;
    }

    @RequestMapping("/save_chooseSub")
    public ModelAndView save_chooseSub(HttpServletRequest request) throws Exception{
        List<Subject> subjects = subjectManager.listEnableSubjects();
        Integer stuId = Integer.parseInt(request.getParameter("stuId"));
        Student student = stuManager.getStudnet(stuId);
        String s = request.getParameter("checkValue");
        String[] subNameList = s.split(",");
        int i = 0;
        Set<Subject> set = new HashSet<>();
        Set<Subject> set1 = new HashSet<>();
        for(String subName:subNameList){
            for(Subject subject:subjects){
                if(Integer.parseInt(subName)==subject.getSubId()) {
                    subject.setSubNum(subject.getSubNum() + 1);
                    subjectManager.updateSubject(subject);
                    set.add(subject);
                }
            }
            i++;
        }
        student.setStuSubjectSize(student.getStuSubjectSize()+i);
        for(Subject ss:student.getSubjects()){
            set.add(ss);
        }
        student.setSubjects(set);
        stuManager.updateStudent(student);
        return new ModelAndView("redirect:/student/student/list.do");
    }

    @RequestMapping("/setMark")
    public ModelAndView setMark(Integer stuId) throws Exception{
        Student student = stuManager.getStudnet(stuId);
        ModelAndView modelAndView = new ModelAndView("student/student/setMark");
        modelAndView.addObject("student",student);
        return modelAndView;
    }
    @RequestMapping("save_setMark")
    public ModelAndView save_setMark(HttpServletRequest request) throws Exception{
        Integer stuId = Integer.parseInt(request.getParameter("stuId"));
        Student student = stuManager.getStudnet(stuId);
        Set<Subject> subjects = student.getSubjects();
        int sumMark = 0 ;
        int stuSubSize = student.getStuSubjectSize();
        for(Subject subject:subjects){
             /**
             *获取到分数
             */
            int mark = Integer.parseInt(request.getParameter(subject.getSubName()));
            sumMark+=mark;


            /**
             * 向科目表录入
             */
            Subject subject1 = subjectManager.getSubject(subject.getSubId());
            int sum = subject1.getSubNum();
            int subAveMark =  subject1.getSubAveMark();
            int newSubAveMark = (sum*subAveMark+mark)/sum;
            subject1.setSubAveMark(newSubAveMark);
            subjectManager.updateSubject(subject1);
        }

        /**
         *向学生表录入
         */
        student.setStuAveMark(sumMark/stuSubSize);
        stuManager.updateStudent(student);
        /**
         * 向班级表录入
         */
        System.out.println("------------------");
        int graId = student.getGrade().getGraId();
        List<Student> students = stuManager.getStudnetByGrade(graId);
        int sum = 0;
        int sumGradeMark = 0 ;
        for (Student student1:students){
            int stuAveMark = student1.getStuAveMark();
            sum++;
            sumGradeMark+=stuAveMark;
        }
        Grade grade = gradeManager.getGradeByGraId(graId);
        grade.setGraAveMark(sumGradeMark/sum);
        gradeManager.updateGrade(grade);
        return new ModelAndView("redirect:/student/student/list.do");
    }

}
