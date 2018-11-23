package com.slicepoker.zps.project.Comprehensive;

import lombok.Data;

import javax.persistence.*;

/**
 * @author Zps
 * @date 2018/11/22 17:35
 **/
@Data
@Entity
@Table(name="tb_comprehensive")
public class Comprehensive {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private Long studentCode;

    private String studentName;


}
