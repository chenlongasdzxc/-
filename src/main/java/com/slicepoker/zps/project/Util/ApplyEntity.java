package com.slicepoker.zps.project.Util;

import lombok.Data;

import javax.persistence.MappedSuperclass;

/**
 * @author Zps
 * @date 2019/3/17 17:11
 **/
@Data
@MappedSuperclass
public class ApplyEntity extends BaseEntily{

    private String value;

    private Long applyPersonNumber; //审核人学号

    private String applyPersonName; // 审核人姓名

    private String comprehensiveQualityStates; //申请综合素质状态

    private Long applyComprehensiveNumber;  //同意申请综合素质人学号

    private String applyComprehensiveName;  //同意申请综合素质人姓名
}
