package com.slicepoker.zps.project.User.Pojo;

import lombok.Data;

import javax.persistence.*;

/**
 * @author Zps
 * @date 2018/11/7 11:22
 **/
@Entity
@Data
@Table(name="tb_moralj")
public class MoralJ {


    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

   private String moralJName;

   private String moralJcode;

}
