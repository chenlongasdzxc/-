package com.slicepoker.zps.project.User.Pojo;

import lombok.Data;

import javax.persistence.*;

/**
 * @author Zps
 * @date 2018/12/25 16:24
 **/
@Data
@Table(name="tb_permission")
@Entity
public class Permission {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private Long roleId; //角色id

    private String name;
}
