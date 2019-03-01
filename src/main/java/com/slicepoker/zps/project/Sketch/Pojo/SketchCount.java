package com.slicepoker.zps.project.Sketch.Pojo;

import com.slicepoker.zps.project.Util.BaseEntily;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author Zps
 * @date 2019/3/1 11:22
 **/
@Table(name="tb_sketchCount")
@Data
@Entity
public class SketchCount extends BaseEntily {

    private Date year;

    private double sketchScore;
}
