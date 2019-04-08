package com.slicepoker.zps.project.Permission.Pojo;

import lombok.Data;

import javax.persistence.*;

/**
 * @author Zps
 * @date 2019/4/4 14:39
 **/

@Data
@Entity
@Table(name="tb_user_role_contact")
public class UserRoleContact {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String userName; //姓名

    private Long studentNumber; //学号

    private String roleName; //角色名称

    private String roleCode; //角色代码

    private boolean deleted = false; //删除
}
