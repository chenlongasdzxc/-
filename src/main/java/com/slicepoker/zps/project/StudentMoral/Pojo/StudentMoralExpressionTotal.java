package com.slicepoker.zps.project.StudentMoral.Pojo;

import com.slicepoker.zps.project.Util.ApplyEntity;
import com.slicepoker.zps.project.Util.BaseEntily;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Zps
 * @date 2019/4/1 15:23
 **/
@Data
@Table(name="tb_student_moral_expression_total")
@Entity
public class StudentMoralExpressionTotal extends BaseEntily {

    private String moralExpressionYear;

    private String moralExpressionNameList;

    private Double moralExpressionTotalScore;

    private boolean deleted=false;
}
