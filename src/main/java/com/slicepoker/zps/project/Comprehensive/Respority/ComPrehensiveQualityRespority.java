package com.slicepoker.zps.project.Comprehensive.Respority;

import com.slicepoker.zps.project.Comprehensive.Pojo.ComprehensiveQuality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @author Zps
 * @date 2019/4/16 16:20
 **/
public interface ComPrehensiveQualityRespority extends JpaRepository<ComprehensiveQuality,Long>, JpaSpecificationExecutor<ComprehensiveQuality> {

    ComprehensiveQuality findByStudentNumberAndComprehensiveQualityYearAndDeletedIsFalse(Long studentNumber,String comprehensiveQualityYear);

    List findByStudentNumberAndDeletedIsFalse(Long studentNumber);
}
