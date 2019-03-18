package com.slicepoker.zps.project.StudentMoral.Pojo;

import com.slicepoker.zps.project.Util.ApplyEntity;
import com.slicepoker.zps.project.Util.BaseEntily;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author Zps
 * @date 2019/3/6 16:15
 **/
@Data
@Entity
@Table(name="tb_studentMoralPlus")
public class StudentMoralPlus extends ApplyEntity {

    private String moralPlusName;  //名称

    private String moralPlusType;  //类型

    private double moralPlusScore; //分数

    private String year; //年度

    private Date updateDate; //上传时间

    private String states; //状态

    private String keyWord; //查询字段

    private boolean deleted=false;

   /* private String value;

    private Long applyPersonNumber; //审核人学号

    private String applyPersonName; // 审核人姓名

    private String comprehensiveQualityStates; //申请综合素质状态

    private Long applyComprehensiveNumber;  //同意申请综合素质人学号

    private String applyComprehensiveName;  //同意申请综合素质人姓名*/
}
