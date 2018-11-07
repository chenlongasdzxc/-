package com.slicepoker.zps.project.Pojo;

import lombok.Data;

import javax.persistence.*;

/**
 * @author Zps
 * @date 2018/11/7 14:22
 **/
@Data
@Entity
@Table(name="tb_studentMiral")
public class StudentMoral {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String studentName;

    private Long studentCode;

    private String studentClass;

    private Long grade;

    private String moralEName;

    private double moralEScore; //德育分

    private String moralJName;

    private double moralJScore;

}
