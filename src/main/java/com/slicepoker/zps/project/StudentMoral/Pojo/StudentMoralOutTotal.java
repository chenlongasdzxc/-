package com.slicepoker.zps.project.StudentMoral.Pojo;

import com.slicepoker.zps.project.Util.BaseEntily;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Zps
 * @date 2019/4/17 15:58
 **/
@Entity
@Data
@Table(name="tb_student_moral_out_total")
public class StudentMoralOutTotal extends BaseEntily {

    private String moralOutNameList; //综合素质课外加分名称list

    private double moralOutTotal; //综合素质课外加分分数

    private String moralOutYear; //综合素质课外加分年度

    private boolean deleted = false;
}
