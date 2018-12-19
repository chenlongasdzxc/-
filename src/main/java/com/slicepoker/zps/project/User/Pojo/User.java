package com.slicepoker.zps.project.User.Pojo;

import lombok.Data;

import javax.persistence.*;

/**
 * @author Zps
 * @date 2018/10/12 16:34
 **/
@Entity
@Data
@Table(name="tb_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userName; //用户名

    private String userPassword; //密码

    private Long studentNumber;  //学号

    private Long gradeLeave;   //等级

    boolean deleted = false;  //删除
}
