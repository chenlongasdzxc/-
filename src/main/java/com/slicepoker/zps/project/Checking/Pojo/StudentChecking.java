package com.slicepoker.zps.project.Checking.Pojo;
import com.slicepoker.zps.project.Util.BaseEntily;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author Zps
 * @date 2019/1/22 16:50
 **/
@Entity
@Table(name="tb_studentChecking")
@Data
public class StudentChecking extends BaseEntily {

    private Date checkingDate;  //考勤时间

    private String checkingName; //缺勤项目名称

    private Long updateStudentNumber; //上传人学号

    private String  updateStudentName; //上传人姓名

    private Date updateDate;  //上传时间

    private boolean deleted=false; //删除

}
