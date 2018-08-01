package com.biz.lesson.model.student;

import com.biz.lesson.model.Persistent;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Subject")
public class Subject  {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(length = 20)
    private Integer subId;

    @Column(length = 50, nullable = false)
    private String subName;

    @Column(length = 20)
    private Integer subNum;

    @Column(length = 50)
    private Integer subAveMark;

    @ManyToMany(mappedBy = "subjects")
    private Set<Student> students;

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public Integer getSubId() {
        return subId;
    }

    public void setSubId(Integer subId) {
        this.subId = subId;
    }

    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }

    public Integer getSubNum() {
        return subNum;
    }

    public void setSubNum(Integer subNum) {
        this.subNum = subNum;
    }

    public Integer getSubAveMark() {
        return subAveMark;
    }

    public void setSubAveMark(Integer subAveMark) {
        this.subAveMark = subAveMark;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "subId=" + subId +
                ", subName='" + subName + '\'' +
                ", subNum=" + subNum +
                ", subAveMark=" + subAveMark +
                '}';
    }

}
