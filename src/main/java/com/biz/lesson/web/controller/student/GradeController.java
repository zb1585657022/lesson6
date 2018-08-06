package com.biz.lesson.web.controller.student;

import com.biz.lesson.Constants;
import com.biz.lesson.business.stu.GradeManager;
import com.biz.lesson.exception.BusinessAsserts;
import com.biz.lesson.model.student.Grade;
import com.biz.lesson.web.controller.BaseController;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/student/grade")
public class GradeController extends BaseController {

    @Autowired
    private GradeManager gradeManager;

    @RequestMapping("/list")
    public ModelAndView list(HttpServletRequest request) throws Exception{
        ModelAndView modelAndView = new ModelAndView("student/grade/list");
        List<Grade> grades = gradeManager.listEnableGrades();
        for (Grade grade:grades){
            grade.setGraNum(gradeManager.findStuNumber(grade.getGraId().longValue()));
            System.out.println(gradeManager.findStuNumber(grade.getGraId().longValue()));
            gradeManager.createGrade(grade);
        }
        request.setAttribute("gradeList" ,grades);
        modelAndView.addObject("systemProperties",gradeManager.listEnableGrades());
        return modelAndView;
    }

    @RequestMapping("/add")
    public ModelAndView add(HttpServletRequest request) throws Exception {

        ModelAndView modelAndView = new ModelAndView("student/grade/add");
        modelAndView.addObject("cmd", "add");
        modelAndView.addObject("roles", gradeManager.listEnableGrades());

        Grade grade = new Grade();
        modelAndView.addObject("grade", grade);

        addReferer(request);
        return modelAndView;
    }

    @RequestMapping("/save_add")
    public ModelAndView save_add(Grade grade, HttpServletRequest request) throws Exception {
//        Grade grade = new Grade();
    //    System.out.println("++++++++++++++++++++++++++++++++");
     //   grade.setGraId(Integer.parseInt(request.getParameter("graId")));
//        grade.setGraName(request.getParameter("graName"));
//        grade.setGraNum(Integer.parseInt(request.getParameter("graNum")));
//        String graAveMarkStr = request.getParameter("graAveMark");
//        System.out.println(2);
//        if(graAveMarkStr!=null&&!"".equals(graAveMarkStr)){
//            grade.setGraNum(Integer.parseInt(request.getParameter(graAveMarkStr)));
//        }
        System.out.println(grade);
        grade.setGraAveMark(0);
        gradeManager.createGrade(grade);
        return referer(request);
    }

    @RequestMapping("/edit")
    public ModelAndView edit(Integer graId, HttpServletRequest request) throws Exception {
        ModelAndView modelAndView = new ModelAndView("student/grade/add");
        Grade grade = gradeManager.getGrade(graId);
        BusinessAsserts.exists(grade, graId);

        modelAndView.addObject("grade", grade);
        modelAndView.addObject("cmd", "edit");
        modelAndView.addObject("roles", gradeManager.listAllRoles());
        addReferer(request);
        return modelAndView;
    }

    @RequestMapping("/save_edit")
    public ModelAndView save_edit(Grade grade, HttpServletRequest request) throws Exception {
//        grade.setGraId(Integer.parseInt(request.getParameter("graId")));
//        grade.setGraName(request.getParameter("graName"));
//        grade.setGraNum(Integer.parseInt(request.getParameter("graNum")));
//        String graAveMarkStr = request.getParameter("graAveMark");
//        if(graAveMarkStr!=null&&!"".equals(graAveMarkStr)){
//            grade.setGraNum(Integer.parseInt(request.getParameter(graAveMarkStr)));
//        }
        BusinessAsserts.exists(grade, grade.getGraId());
        gradeManager.updateGrade(grade);
        return referer(request);
    }

    @RequestMapping("/graId/delete")
    public ModelAndView save_delete(@RequestParam("graId") Integer graId , HttpServletRequest request) throws Exception{
        Grade grade = gradeManager.getGrade(graId);
        Boolean b = null;
        try {
            gradeManager.deleteGrade(grade);
            b =  true;
        } catch (Exception e) {
            b =  false;
        }
        String referer = request.getHeader(Constants.REFERER);
        if (StringUtils.isEmpty(referer)) {
            referer = "/student/grade/list.do";
        }

        return new ModelAndView("redirect:"+referer);
    }

}
