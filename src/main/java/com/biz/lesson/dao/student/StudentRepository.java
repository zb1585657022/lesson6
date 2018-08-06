package com.biz.lesson.dao.student;

import com.biz.lesson.dao.common.CommonJpaRepository;
import com.biz.lesson.model.student.Student;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends CommonJpaRepository<Student, Integer>,StudentDao,CrudRepository<Student,Integer> {

   List<Student> findAll();

   @Modifying
   @Query("DELETE from Student s WHERE s.stuId = :stuId")
   void disableStudent(@Param("stuId") Integer stuId);

   @Modifying
   @Query("select s from Student s WHERE s.grade.graId = :graId")
   List<Student> findByGrade_GraId(@Param("graId")Integer graId);




}
