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

    private String studentName; //姓名

    private String className;  //班级

    private String grade;  //年级

    private Long studentNumber;  //学号

    private String collegeName;  //学院名称

    private Long gradeLeave;   //等级

    boolean deleted = false;  //删除
}
