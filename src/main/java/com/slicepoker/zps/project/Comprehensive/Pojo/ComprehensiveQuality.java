package com.slicepoker.zps.project.Comprehensive.Pojo;

import com.slicepoker.zps.project.Util.ApplyEntity;
import com.slicepoker.zps.project.Util.BaseEntily;
import lombok.Data;

import javax.persistence.*;

/**
 * @author Zps
 * @date 2018/11/7 11:36
 **/
@Entity
@Data
@Table(name="tb_comprehensive_quality")
public class ComprehensiveQuality extends ApplyEntity {

    private String moralExpressionNameList; //德育表现分名称list

    private double moralExpressionScore = 0; //德育表现分

    private String moralOutNameList;  //课外加分名称list

    private double moralOutScore = 0;  //课外加分

    private String moralPlusNameList; //德育加分名称list

    private double moralPlusScore = 0; //德育加分

    private String moralDeductionNameList; //德育减分名称list

    private double moralDeductionScore = 0; //德育减分

    private boolean cetFour = false;  //英语四级

    private boolean nCRE = false; //计算机二级

    private double comprehensiveQualityScore; //综合素质总分

    private String comprehensiveQualityYear; //综合素质年度

    private boolean deleted = false;
}
