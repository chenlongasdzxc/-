package com.slicepoker.zps.project.Grade.Pojo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import com.slicepoker.zps.project.Util.BaseEntily;
import lombok.Data;

import javax.persistence.*;

/**
 * @author Zps
 * @date 2019/4/22 15:40
 **/
@Data
@Entity
@Table(name="tb_student_grade")
@ExcelTarget("studentInformation")
public class StudentGrade extends BaseEntily {

    @Excel(name="学年")
    private String gradeYear;
    @Excel(name="科目名称")
    private String gradeName;
    @Excel(name="科目成绩")
    private double gradeScore;
    @Excel(name="绩点")
    private double score;

    private String states;

    private double gpa; //单科绩点

    private boolean warning = false;

    private boolean deleted = false;
}
