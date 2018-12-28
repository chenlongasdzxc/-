package com.slicepoker.zps.project.Comprehensive.Pojo;

import com.slicepoker.zps.project.Util.BaseEntily;
import lombok.Data;

import javax.persistence.*;

/**
 * @author Zps
 * @date 2018/11/7 11:36
 **/
@Entity
@Data
@Table(name="tb_comprehensiveQuality")
public class ComprehensiveQuality extends BaseEntily {


    private double moralEScore; //德育表现分

    private String moralJName; //加分项目名称

    private double moralJScore; //加分分值

    private String moralSName; //减分项目名称

    private double moralSScore; //减分分值

    private String outSchoolName; //F课外项目名称

    private double outSchoolScore; //F课外分值

    private double gpa;  //绩点

    private double gpaScore; //绩点得分  35+7gpa
}
