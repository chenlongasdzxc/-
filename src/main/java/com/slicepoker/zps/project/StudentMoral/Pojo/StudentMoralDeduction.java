package com.slicepoker.zps.project.StudentMoral.Pojo;

import com.slicepoker.zps.project.Util.ApplyEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Zps
 * @date 2019/4/15 14:34
 **/
@Data
@Table(name="tb_student_moral_deduction")
@Entity
public class StudentMoralDeduction extends ApplyEntity {

    private String moralDeductionType;   //德育减分名称

    private double moralDeductionScore;  //德育减分分数

    private String moralDeductionYear; //德育减分年度

    private double moralDeductionNumber; //德育减分次数

    private String description; //描述

    private boolean deleted = false;

    private String keyWord; //查询字段
}
