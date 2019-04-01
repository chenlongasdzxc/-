package com.slicepoker.zps.project.StudentMoral.Pojo;

import com.slicepoker.zps.project.Util.ApplyEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Zps
 * @date 2019/3/22 16:41
 **/
@Data
@Table(name="tb_student_moral_expression")
@Entity
public class StudentMoralExpression extends ApplyEntity {

    private String moralExpressionName;   //德育表现名称

    private double moralExpressionScore;  //德育表现分数

    private String moralExpressionYear; //德育表现年度

    private boolean deleted = false;

    private String keyWord; //查询字段


}
