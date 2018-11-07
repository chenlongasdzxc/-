package com.slicepoker.zps.project.Pojo;

import lombok.Data;

import javax.persistence.*;

/**
 * @author Zps
 * @date 2018/11/7 10:32
 **/
@Data
@Entity
@Table(name="tb_morale")
public class MoralE {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String moralEName;

    private String moralECode;

}
