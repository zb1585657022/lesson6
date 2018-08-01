package com.biz.lesson.business.stu;

import com.biz.lesson.business.BaseService;
import com.biz.lesson.dao.student.GradeRepository;
import com.biz.lesson.dao.student.SubjectRepository;
import com.biz.lesson.event.CacheEventType;
import com.biz.lesson.event.event.CacheEvent;
import com.biz.lesson.model.student.Grade;
import com.biz.lesson.model.student.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class SubjectManager extends BaseService {

    private static final Logger logger = LoggerFactory.getLogger(StuManager.class);

    @Autowired
    private SubjectRepository subjectRepository;

    public List<Subject> listEnableSubjects() {
        return subjectRepository.findAll();
    }

    public void createSubject(Subject subject) {
        subjectRepository.save(subject);
        publishEvent(new CacheEvent(this,CacheEventType.USER));
    }

    public Subject getSubject(Integer subId) {
        return subjectRepository.findOne(subId);
    }

    public List<Subject> listAllRoles() {
        return subjectRepository.findAll();
    }

    public void updateSubject(Subject subject) {
        subjectRepository.save(subject);
        publishEvent(new CacheEvent(this,CacheEventType.USER));
    }

    public void deleteSubject(Subject subject) {
        subjectRepository.disableSubject(subject.getSubId());
        publishEvent(new CacheEvent(this,CacheEventType.USER));
    }


}
