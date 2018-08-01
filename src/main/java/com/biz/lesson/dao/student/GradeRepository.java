package com.biz.lesson.dao.student;

import com.biz.lesson.dao.common.CommonJpaRepository;
import com.biz.lesson.model.student.Grade;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GradeRepository extends CommonJpaRepository<Grade,Integer> ,GradeDao {

    List<Grade> findAll();

    @Modifying
    @Query("DELETE from Grade g WHERE g.graId = :graId")
    void disableGrade(@Param("graId") Integer graId);

    Grade findByGraName(String graName);

    @Query(nativeQuery = true, value = "select  COUNT(DISTINCT stuid) FROM student WHERE grade_id = ?")
    Integer findStuNumber(Long graId);
}
