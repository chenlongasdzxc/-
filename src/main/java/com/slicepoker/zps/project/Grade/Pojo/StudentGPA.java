package com.slicepoker.zps.project.Grade.Pojo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import javax.persistence.*;

/**
 * @author Zps
 * @date 2019/4/23 14:28
 **/

@Data
@Entity
@Table(name="tb_student_gpa")
public class StudentGPA {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String year; //学年

    private String major; //专业

    private String gpaName; //要计算绩点科目名称

    private double gpaScore; //成绩-gpaScore

    private double gpaNameCount; //

    private boolean deleted = false;
}
