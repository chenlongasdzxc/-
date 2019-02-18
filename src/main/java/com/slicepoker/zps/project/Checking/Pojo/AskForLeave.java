package com.slicepoker.zps.project.Checking.Pojo;

import com.slicepoker.zps.project.Util.BaseEntily;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author Zps
 * @date 2019/2/9 22:04
 **/
@Table(name="tb_studentLeave")
@Data
@Entity
public class AskForLeave extends BaseEntily {

    private String leaveName; //请假课程

    private Date leaveDateStart; //请假日期开始

    private Date leaveDateEnd;  //请假日期结束

    private Date upDate; //上传时间

    private String leaveValue; //请假理由

    private boolean deleted=false; //删除

    protected String keyWord; //查询字段

    private String states; //状态

    private String applyValue; //审核理由
}
