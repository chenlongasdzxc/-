package com.slicepoker.zps.project.Sketch.Pojo;

import com.slicepoker.zps.project.Util.BaseEntily;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Zps
 * @date 2019/2/26 16:39
 **/
@Data
@Entity
@Table(name="tb_sketchAnalysis")
public class SketchAnalysis extends BaseEntily {

    private String sketchTypeName;

    private double scoreCount;

    private String keyWord;
}
