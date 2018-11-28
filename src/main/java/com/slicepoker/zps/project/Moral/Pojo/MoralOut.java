package com.slicepoker.zps.project.Moral.Pojo;

import lombok.Data;

import javax.persistence.*;

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

    private String foutName; //课外加分名称

    private String foutScore; //课外加分分数

    private Long foutCode;  //课外加分Code
}
