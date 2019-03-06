package com.slicepoker.zps.project.Moral.Pojo;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Zps
 * @date 2018/11/22 16:15
 * @description 课外加分项目实体类
 **/
@Data
@Entity
@Table(name="tb_moralout")
public class MoralOut {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String moralOutName; //课外加分名称

    private double moralOutScore; //课外加分分数

    private String moralOutType;  //课外加分类型

    private String value; //描述

    private String states; //级别

    private Date createTime; //创建时间

    private boolean deleted=false; //删除

    protected String keyWord; //查询字段
}
