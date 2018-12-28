package com.slicepoker.zps.project.Sketch.Pojo;

import com.slicepoker.zps.project.Util.BaseEntily;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Zps
 * @date 2018/11/2 13:57
 * @description 素拓分总表
 **/
@Entity
@Table(name="tb_sketch")
@Data
public class Sketch extends BaseEntily {

    private String sketchName;  //素拓名称

    private String type; //素拓类型

    private double sketchScore; //素拓分

    private Date createDate;  //创建时间

    private String sketchPart; //角色

    private String sketchTypeCode;

    private boolean deleted=false;  //删除

    private boolean sketchStates=false; //状态

}
