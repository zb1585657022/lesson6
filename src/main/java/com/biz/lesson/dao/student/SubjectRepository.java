package com.biz.lesson.dao.student;

import com.biz.lesson.dao.common.CommonJpaRepository;
import com.biz.lesson.model.student.Subject;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectRepository extends CommonJpaRepository<Subject,Integer> ,SubjectDao {

    List<Subject> findAll();

    @Modifying
    @Query("DELETE from Subject sub WHERE sub.subId = :subId")
    void disableSubject(@Param("subId") Integer subId);
}
