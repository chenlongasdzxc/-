package com.slicepoker.zps.project.User.Pojo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
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
@ExcelTarget("studentInformation")
public class StudentInformation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Excel(name= "姓名")
    private String studentName; //姓名
    @Excel(name="学号")
    private Long studentNumber;  //学号
    @Excel(name="籍贯")
    private String originPlace; //籍贯
    @Excel(name="名族")
    private String nation; //民族
    @Excel(name="年级")
    private Long grade;  //年级
    @Excel(name="联系电话")
    private Long phoneNumber;  //联系电话
    @Excel(name="邮箱")
    private String email;  //邮箱

    private Integer sex;  //男：1  女：0
    @Excel(name="专业")
    private String major; //专业
    @Excel(name="班级")
    private String studentClass;  //班级
    @Excel(name="职务")
    private String duty;  //职务
    @Excel(name="寝室号")
    private String roomNumber; //寝室号
    @Excel(name="身份证号码")
    private String idCard; //身份证号码
    @Excel(name="银行卡号")
    private String bankNumber; //银行卡号
    @Excel(name="开户银行")
    private String bankName; //开户银行
    @Excel(name="家庭住址")
    private String adress; //家庭住址
    @Excel(name="政治面貌")
    private String politicesStatus; //政治面貌(群众，共青团员，预备党员，党员)
    @Excel(name="建档立卡")
    private boolean fileCard = false; //是否建档立卡（默认为否）

    @Transient
    protected String findWord;   //查询字段

    private boolean deleted = false;
}
