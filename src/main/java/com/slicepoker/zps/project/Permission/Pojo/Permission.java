package com.slicepoker.zps.project.Permission.Pojo;

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

    private String permissionName; //权限名称

    private String permissionCode; //权限代码

    private String value; //描述

    private boolean deleted = false; //删除
}
