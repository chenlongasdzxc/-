package com.slicepoker.zps.project.User.Pojo;

import lombok.Data;

import javax.persistence.*;

/**
 * @author Zps
 * @date 2018/12/25 16:20
 **/
@Data
@Entity
@Table(name="tb_role")
public class Role {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private Long userId; //用户ID

    private String roleName; //角色名称
}
