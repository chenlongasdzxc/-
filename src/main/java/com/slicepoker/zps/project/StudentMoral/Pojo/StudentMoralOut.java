package com.slicepoker.zps.project.StudentMoral.Pojo;

import com.slicepoker.zps.project.Util.ApplyEntity;
import com.slicepoker.zps.project.Util.BaseEntily;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author Zps
 * @date 2019/3/14 8:51
 **/
@Data
@Entity
@Table(name="tb_studentMoralOut")
public class StudentMoralOut extends ApplyEntity {

    private String moralOutName;  //名称

    private String moralOutType;  //类型

    private double moralOutScore; //分数

    private String year; //年度

    private Date updateDate; //上传时间

    private String keyWord; //查询字段

    private boolean deleted=false;
}
