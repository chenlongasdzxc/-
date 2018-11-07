package com.slicepoker.zps.project.Respority;

import com.slicepoker.zps.project.Pojo.Sketch;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @author Zps
 * @date 2018/11/2 14:07
 **/
public interface SketchRespority extends JpaRepository<Sketch,Long>, JpaSpecificationExecutor<Sketch> {

    List findByClassNameAndDeletedIsFalse(String className);

    /*Sketch findByStudentNumberAndDeletedIsFalse(Long studentNumber);*/

    Sketch findByIdAndDeletedIsFalse(Long id);

    Sketch findByIdAndDeletedIsFalseAndAndSketchStatesIsFalse(Long id);
}
