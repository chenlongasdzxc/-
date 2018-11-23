package com.slicepoker.zps.project.Moral.Pojo;

import lombok.Data;

import javax.persistence.*;

/**
 * @author Zps
 * @date 2018/11/7 14:22
 **/
@Data
@Entity
@Table(name="tb_studentMiral")
public class StudentMoral {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String studentName;  //姓名

    private Long studentCode;  //学号

    private String studentClass;  //班级

    private Long grade;  //年级

    private String moralEName; //

    private double moralEScore; //德育分

    private String moralJName;  //课外加分项目

    private double moralJScore;  //课外加分分数

}
