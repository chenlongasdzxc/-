package com.slicepoker.zps.project.Pojo;

import lombok.Data;

import javax.persistence.*;

/**
 * @author Zps
 * @date 2018/11/2 15:06
 **/
@Entity
@Table(name="tb_sketchscore")
@Data
public class SketchScore {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type; //类型

    private double participant; //参与者0.3

    private double organizer; //组织者0.5

    private double winnerOne; //获奖者1

    private double winnerTwo; //获奖者2

    private double winnerThree; //获奖者3

    private String sketchTypeCode; //代码

    private boolean deleted=false;
}
