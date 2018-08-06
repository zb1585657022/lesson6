package com.biz.lesson.web.controller.student;

import com.biz.lesson.business.stu.SubjectManager;
import com.biz.lesson.exception.BusinessAsserts;
import com.biz.lesson.model.student.Subject;
import com.biz.lesson.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/student/subject")
public class SubjectController extends BaseController {

    @Autowired
    private SubjectManager subjectManager;

    @RequestMapping("/list")
    public ModelAndView list(HttpServletRequest request) throws Exception{
        ModelAndView modelAndView = new ModelAndView("student/subject/list");
        List<Subject> subjects = subjectManager.listEnableSubjects();
        request.setAttribute("subjectList" ,subjects);
        modelAndView.addObject("systemProperties",subjects);
        return modelAndView;
    }

    @RequestMapping("/add")
    public ModelAndView add(HttpServletRequest request) throws Exception {

        ModelAndView modelAndView = new ModelAndView("student/subject/add");
        modelAndView.addObject("cmd", "add");
        modelAndView.addObject("roles", subjectManager.listEnableSubjects());

        Subject subject = new Subject();
        modelAndView.addObject("subject", subject);

        addReferer(request);
        return modelAndView;
    }

    @RequestMapping("/save_add")
    public ModelAndView save_add(Subject subject, HttpServletRequest request) throws Exception {
//        Grade grade = new Grade();
     //   grade.setGraId(Integer.parseInt(request.getParameter("graId")));
//        grade.setGraName(request.getParameter("graName"));
//        grade.setGraNum(Integer.parseInt(request.getParameter("graNum")));
//        String graAveMarkStr = request.getParameter("graAveMark");
//        System.out.println(2);
//        if(graAveMarkStr!=null&&!"".equals(graAveMarkStr)){
//            grade.setGraNum(Integer.parseInt(request.getParameter(graAveMarkStr)));
//        }
        subject.setSubAveMark(0);
        subjectManager.createSubject(subject);
        return referer(request);
    }

    @RequestMapping("/edit")
    public ModelAndView edit(Integer subId, HttpServletRequest request) throws Exception {
        ModelAndView modelAndView = new ModelAndView("student/subject/add");
        Subject subject = subjectManager.getSubject(subId);
        BusinessAsserts.exists(subject, subId);

        modelAndView.addObject("subject", subject);
        modelAndView.addObject("cmd", "edit");
        modelAndView.addObject("roles", subjectManager.listEnableSubjects());
        addReferer(request);
        return modelAndView;
    }

    @RequestMapping("/save_edit")
    public ModelAndView save_edit(Subject subject, HttpServletRequest request) throws Exception {
//        grade.setGraId(Integer.parseInt(request.getParameter("graId")));
//        grade.setGraName(request.getParameter("graName"));
//        grade.setGraNum(Integer.parseInt(request.getParameter("graNum")));
//        String graAveMarkStr = request.getParameter("graAveMark");
//        if(graAveMarkStr!=null&&!"".equals(graAveMarkStr)){
//            grade.setGraNum(Integer.parseInt(request.getParameter(graAveMarkStr)));
//        }
        BusinessAsserts.exists(subject, subject.getSubId());
        subjectManager.updateSubject(subject);
        return referer(request);
    }

    @RequestMapping("/subId/delete")
    public ModelAndView save_delete(@RequestParam("subId") Integer subId , HttpServletRequest request) throws Exception{
        Subject subject = subjectManager.getSubject(subId);
        Boolean b = null;
        try {
            subjectManager.deleteSubject(subject);
            b =  true;
        } catch (Exception e) {
            b =  false;
        }
        return referer(request);
    }

}
