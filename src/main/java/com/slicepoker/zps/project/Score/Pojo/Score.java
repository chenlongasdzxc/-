package com.slicepoker.zps.project.Score.Pojo;

import lombok.Data;

import javax.persistence.*;

/**
 * @author Zps
 * @date 2018/11/22 14:25
 * @description 成绩实体类
 **/
@Data
@Entity
@Table(name="tb_score")
public class Score {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private Long studentCode;  //学号

    private String studentName;  //学生姓名

    private String subjectName;  //科目名称

    private double credit;  //学分

    private int term;   //学期

    private double score; //成绩
}
