package com.biz.lesson.business.stu;

import com.biz.lesson.business.BaseService;
import com.biz.lesson.dao.student.GradeRepository;
import com.biz.lesson.event.CacheEventType;
import com.biz.lesson.event.event.CacheEvent;
import com.biz.lesson.model.student.Grade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional
@Service
public class GradeManager extends BaseService {

    private static final Logger logger = LoggerFactory.getLogger(StuManager.class);

    @Autowired
    private GradeRepository gradeRepository;

    public List<Grade> listEnableGrades() {
        return gradeRepository.findAll();
    }

    public void createGrade(Grade grade) {
        gradeRepository.save(grade);
        publishEvent(new CacheEvent(this,CacheEventType.USER));
    }

    public Grade getGrade(Integer graId) {
        return gradeRepository.findOne(graId);
    }

    public List<Grade> listAllRoles() {
        return gradeRepository.findAll();
    }

    public void updateGrade(Grade grade) {
        gradeRepository.save(grade);
        publishEvent(new CacheEvent(this,CacheEventType.USER));
    }

    public void deleteGrade(Grade grade) {
        gradeRepository.disableGrade(grade.getGraId());
        publishEvent(new CacheEvent(this,CacheEventType.USER));
    }

    /**
     * 通过名字找到班级对象
     * @param graName
     * @return
     */
    public Grade findByName(String graName){
        return gradeRepository.findByGraName(graName);
    }

    public Integer findStuNumber(Long graId){
        return gradeRepository.findStuNumber(graId);
    }
}
