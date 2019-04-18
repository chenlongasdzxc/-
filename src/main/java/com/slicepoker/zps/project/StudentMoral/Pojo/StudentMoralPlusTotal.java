package com.slicepoker.zps.project.StudentMoral.Pojo;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.slicepoker.zps.project.Util.BaseEntily;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Zps
 * @date 2019/4/18 14:28
 **/
@Data
@Entity
@Table(name="tb_student_moral_plus_total")
public class StudentMoralPlusTotal extends BaseEntily {

    private String moralPlusNameList;

    private String moralPlusYear;

    private double moralPlusTotal;

    private boolean deleted = false;
}
