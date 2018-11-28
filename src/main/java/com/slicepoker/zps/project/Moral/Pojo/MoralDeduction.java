package com.slicepoker.zps.project.Moral.Pojo;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Zps
 * @date 2018/11/28 15:56
 * @description 德育减分项目实体类
 **/
@Data
@Entity
@Table(name="tb_moralDeduction")
public class MoralDeduction {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String moralDeductionName; //减分项目名称

    private double moralDeductionScore;  //减分项目分数

    private String moralDeductionType; //减分项目类型

    private Date createTime; //创建时间

    private boolean deleted=false;
}
