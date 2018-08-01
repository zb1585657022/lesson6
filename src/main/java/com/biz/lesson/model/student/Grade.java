package com.biz.lesson.model.student;

import com.biz.lesson.model.Persistent;

import javax.persistence.*;

@Entity
@Table(name = "Grade")
public class Grade {
    @Id
    @GeneratedValue
    @Column(length = 20)
    private Integer graId;

    @Column(length = 50, nullable = false)
    private String graName;

    @Column(length = 20)
    private Integer graNum;

    @Column(length = 50)
    private Integer graAveMark;

    public Integer getGraId() {
        return graId;
    }

    public void setGraId(Integer graId) {
        this.graId = graId;
    }

    public String getGraName() {
        return graName;
    }

    public void setGraName(String graName) {
        this.graName = graName;
    }

    public Integer getGraNum() {
        return graNum;
    }

    public void setGraNum(Integer graNum) {
        this.graNum = graNum;
    }

    public Integer getGraAveMark() {
        return graAveMark;
    }

    public void setGraAveMark(Integer graAveMark) {
        this.graAveMark = graAveMark;
    }

    @Override
    public String toString() {
        return "Grade{" +
                "graId=" + graId +
                ", graName='" + graName + '\'' +
                ", graNum=" + graNum +
                ", graAveMark=" + graAveMark +
                '}';
    }
}
