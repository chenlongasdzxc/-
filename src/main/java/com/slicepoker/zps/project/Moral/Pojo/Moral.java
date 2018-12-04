package com.slicepoker.zps.project.Moral.Pojo;

import lombok.Data;

import javax.persistence.*;

/**
 * @author Zps
 * @date 2018/11/26 17:01
 **/
@Entity
@Data
@Table(name="tb_moral")
public class Moral {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private Long studentCode;  //学号

    private String studentName;  //姓名

    private String politicalThought; //

    private double politicalThoughtScore;

    private Long poliPeopleCode; //创建人学号

    private String learningAttitude;//学习态度
}
