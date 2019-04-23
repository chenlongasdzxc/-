package com.slicepoker.zps.project.Util;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.Data;

import javax.persistence.*;

/**
 * @author Zps
 * @date 2018/12/20 10:35
 **/
@Data
@MappedSuperclass
@ExcelTarget("studentInformation")
public class BaseEntily {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Excel(name= "年级")
    private Long grade;
    @Excel(name= "专业")
    private String major;
    @Excel(name= "班级")
    private String studentClass;
    @Excel(name= "姓名")
    private String studentName;
    @Excel(name= "学号")
    private Long studentNumber;
}
