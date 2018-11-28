package com.slicepoker.zps.project.Moral.Pojo;

import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Zps
 * @date 2018/11/28 14:04
 * @description 德育加分项目实体类
 **/
@Data
@Entity
@Table(name="tb_moralplus")
public class MoralPlus {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String moralPlusName; //加分项目名称

    private double moralPlusScore; //分值

    private String moralPlusType; //加分项目类型

    private Date createTime; //创建时间

    private boolean deleted=false;

}
