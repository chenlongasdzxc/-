package com.slicepoker.zps.project.Student.Pojo;

import com.slicepoker.zps.project.Util.BaseEntily;
import lombok.Data;

import javax.persistence.*;

/**
 * @author Zps
 * @date 2018/11/7 14:22
 **/
@Data
@Entity
@Table(name="tb_studentMoral")
public class StudentMoral extends BaseEntily {

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

    private boolean deleted=false; //删除

    private String year; //学年

    private String keyWord; //查询字段

}
