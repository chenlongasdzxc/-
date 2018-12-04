package com.slicepoker.zps.project.User.Pojo;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Zps
 * @date 2018/10/18 15:05
 **/
@Entity
@Table(name="TB_StudentInformation")
@Data
public class StudentInformation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String studentName; //姓名

    private Long studentNumber;  //学号

    private Long grade;  //年级

    private Long phoneNumber;  //联系电话

    private String email;  //邮箱

    private Integer sex;  //男：1  女：0

    private String major; //专业

    private String studentClass;  //班级

    private String duty;  //职务

    private String roomNumber; //寝室号

    private Long idCard; //身份证号码

    private Long bankNumber; //银行卡号

    private String bankName; //开户银行

    private String adress; //家庭住址

    private String politicesStatus; //政治面貌(群众，共青团员，预备党员，党员)

    private boolean fileCard = false; //是否建档立卡（默认为否）
}
