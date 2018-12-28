package com.slicepoker.zps.project.Student.Pojo;

import com.slicepoker.zps.project.Util.BaseEntily;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Zps
 * @date 2018/12/4 15:58
 **/
@Data
@Entity
@Table(name="tb_studentMoralExpression")
public class StudentMoralExpression extends BaseEntily {

    private String moralExpressionName; //德育表现名称

    private double moralExpressionScore; //德育表现分数

    private Date createDate; //创建时间

    private Long creatStudentNumber; //创建人学号

    private String createStudentName; //创建人姓名
}
