package com.slicepoker.zps.project.Grade.Pojo;

import lombok.Data;

import javax.persistence.*;

/**
 * @author Zps
 * @date 2019/4/24 10:21
 **/
@Data
@Entity
@Table(name="tb_gpa")
public class Gpa {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String year; //学年

    private String major; //专业

    private String gpaNameList; //要计算绩点科目名称

    private double gpaScore; //成绩-gpaScore

    private double creditCount; //要计算学分总计

    private boolean deleted = false;
}
