package com.slicepoker.zps.project.Moral.Pojo;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Zps
 * @date 2018/11/26 17:20
 * @description 德育表现
 **/
@Data
@Entity
@Table(name="tb_moralExpression")
public class MoralExpression {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String moralExpressionName; //德育表现名称

    private String moralExpressionCode; //德育表现代码

    private Date createDate; //创建时间

    private boolean deleted =false; //删除
}
