package com.slicepoker.zps.project.Grade.Respority;

import com.slicepoker.zps.project.Grade.Pojo.Gpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @author Zps
 * @date 2019/4/24 10:20
 **/
public interface GpaRespority extends JpaRepository<Gpa,Long>, JpaSpecificationExecutor<Gpa> {

    List<Gpa> findAllByDeletedIsFalse();
}
