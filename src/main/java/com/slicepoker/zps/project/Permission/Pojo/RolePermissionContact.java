package com.slicepoker.zps.project.Permission.Pojo;

import lombok.Data;

import javax.persistence.*;

/**
 * @author Zps
 * @date 2019/4/4 14:40
 **/
@Data
@Entity
@Table(name="tb_role_permission_contact")
public class RolePermissionContact {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String roleName; //角色名称

    private String roleCode; //角色代码

    private String permissionName; //权限名称

    private String permissionCode; //权限代码

    private boolean deleted = false; //删除
}
