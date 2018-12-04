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

    private double moralExpression; //德育表现

    private String moralPlusName; //德育加分项目名称

    private double moralPlusScore; //德育加分项目分数

    private String moralDeductionName;  //德育减分项目名称

    private double moralDeductionScore; //德育减分项目分数

    private double scoreOfK; //K值

    private String moralOutName; //课外加分项目名称

    private double moralOutScore;  //课外加分项目分数

    private double averageScore; //平均绩点

    private double score; //绩点得分

    private double scoreSum; //总分

}
