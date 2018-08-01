package com.biz.lesson.business.stu;

import com.biz.lesson.business.BaseService;
import com.biz.lesson.dao.student.StudentRepository;
import com.biz.lesson.event.CacheEventType;
import com.biz.lesson.event.event.CacheEvent;
import com.biz.lesson.model.student.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional
@Service
public class StuManager extends BaseService {

    private static final Logger logger = LoggerFactory.getLogger(StuManager.class);

    @Autowired
    private StudentRepository studentRepository;

    public List<Student> listEnableStudents() {
        return studentRepository.findAll();
    }

    public void createStudent(Student stu) {
        studentRepository.save(stu);
        publishEvent(new CacheEvent(this,CacheEventType.USER));
    }

    public List<Student> listAllRoles() {
        return studentRepository.findAll();
    }


    public Student getStudnet(Integer stuId) {
        return studentRepository.findOne(stuId);
    }

    public void updateStudent(Student student) {
        studentRepository.save(student);
        publishEvent(new CacheEvent(this,CacheEventType.USER));
    }

    public void deleteStudent(Student student) {
        studentRepository.disableStudent(student.getStuId());
        publishEvent(new CacheEvent(this,CacheEventType.USER));
    }

}
