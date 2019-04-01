package com.slicepoker.zps.project.Util;

import lombok.Data;

import javax.persistence.*;

/**
 * @author Zps
 * @date 2018/12/20 10:35
 **/
@Data
@MappedSuperclass
public class BaseEntily {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private Long studentNumber;

    private Long grade;

    private String studentClass;

    private String studentName;

    private String major;
}
