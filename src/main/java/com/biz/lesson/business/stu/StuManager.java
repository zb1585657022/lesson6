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

    /**
     * 查询所有学生
     * @return
     */
    public List<Student> listEnableStudents() {
        return studentRepository.findAll();
    }

    /**
     * 添加一个学生
     * @param stu
     */
    public void createStudent(Student stu) {
        studentRepository.save(stu);
        publishEvent(new CacheEvent(this,CacheEventType.USER));
    }

    /**
     * 通过Id查找学生
     * @param stuId
     * @return
     */
    public Student getStudnet(Integer stuId) {
        return studentRepository.findOne(stuId);
    }

    /**
     * 通过班级Id查找学生
     * @param stuId
     * @return
     */
    public List<Student> getStudnetByGrade(Integer graId) {
        return studentRepository.findByGrade_GraId(graId);
    }

    /**
     * 修改学生信息
     * @param student
     */
    public void updateStudent(Student student) {
        studentRepository.save(student);
        publishEvent(new CacheEvent(this,CacheEventType.USER));
    }

    /**
     * 删除学生
     * @param student
     */
    public void deleteStudent(Student student) {
        studentRepository.disableStudent(student.getStuId());
        publishEvent(new CacheEvent(this,CacheEventType.USER));
    }

}
