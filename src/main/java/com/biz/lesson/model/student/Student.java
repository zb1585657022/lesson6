package com.biz.lesson.model.student;

import com.biz.lesson.model.Persistent;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue
    @Column(length = 20)
    private Integer stuId;

    @Column(length = 50, nullable = false)
    private String stuName;

    @Column(length = 50, nullable = false)
    private String stuSex;

    @Column(length = 50, nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date stuBirthday;

    @Column(length = 50, nullable = false)
    private String stuGrade;

    @Column(length = 50)
    private Integer stuSubjectSize;

    @Column(length = 50)
    private Integer stuAveMark;

    @ManyToOne
    @JoinColumn(name = "grade_id")
    private Grade grade;

    @ManyToMany
    @JoinTable(name = "student_subject", joinColumns = {
            @JoinColumn(name = "stu_id", referencedColumnName = "stuId")},
            inverseJoinColumns = {
            @JoinColumn(name = "sub_id", referencedColumnName = "subId")})
    private Set<Subject> subjects;

    public Integer getStuId() {
        return stuId;
    }

    public void setStuId(Integer stuId) {
        this.stuId = stuId;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getStuSex() {
        return stuSex;
    }

    public void setStuSex(String stuSex) {
        this.stuSex = stuSex;
    }

    public Date getStuBirthday() {
        return stuBirthday;
    }

    public void setStuBirthday(Date stuBirthday) {
        this.stuBirthday = stuBirthday;
    }

    public String getStuGrade() {
        return stuGrade;
    }

    public void setStuGrade(String stuGrade) {
        this.stuGrade = stuGrade;
    }

    public Integer getStuSubjectSize() {
        return stuSubjectSize;
    }

    public void setStuSubjectSize(Integer stuSubjectSize) {
        this.stuSubjectSize = stuSubjectSize;
    }

    public Integer getStuAveMark() {
        return stuAveMark;
    }

    public void setStuAveMark(Integer stuAveMark) {
        this.stuAveMark = stuAveMark;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public Set<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(Set<Subject> subjects) {
        this.subjects = subjects;
    }

    @Override
    public String toString() {
        return "Student{" +
                "stuId=" + stuId +
                ", stuName='" + stuName + '\'' +
                ", stuSex='" + stuSex + '\'' +
                ", stuBirthday=" + stuBirthday +
                ", stuGrade='" + stuGrade + '\'' +
                ", stuSubjectSize=" + stuSubjectSize +
                ", stuAveMark=" + stuAveMark +
                '}';
    }
}
