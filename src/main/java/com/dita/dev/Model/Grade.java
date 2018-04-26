package com.dita.dev.Model;

import javax.persistence.*;

@Entity
@Table(name = "Grades")
public class Grade {
    private Integer id;
    private String token;

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
   // @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getCourse_Code() {
        return Course_Code;
    }

    public void setCourse_Code(String course_Code) {
        Course_Code = course_Code;
    }

    public Double getIntialScore() {
        return intialScore;
    }

    public void setIntialScore(Double intialScore) {
        this.intialScore = intialScore;
    }

    public Double getItem_1() {
        return item_1;
    }

    public void setItem_1(Double item_1) {
        this.item_1 = item_1;
    }

    public Double getItem_2() {
        return item_2;
    }

    public void setItem_2(Double item_2) {
        this.item_2 = item_2;
    }

    public Double getItem_3() {
        return item_3;
    }

    public void setItem_3(Double item_3) {
        this.item_3 = item_3;
    }

    public String getFinal_Grade() {
        return final_Grade;
    }

    public void setFinal_Grade(String final_Grade) {
        this.final_Grade = final_Grade;
    }

    public Double getFinal_mark() {
        return final_mark;
    }

    public void setFinal_mark(Double final_mark) {
        this.final_mark = final_mark;
    }

    private String Course_Code;
    private Double intialScore;
    private Double item_1;
    private Double item_2;
    private Double item_3;
    private String final_Grade;
    private Double final_mark;



}
