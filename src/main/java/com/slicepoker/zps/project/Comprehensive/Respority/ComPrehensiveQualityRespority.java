package com.slicepoker.zps.project.Comprehensive.Respority;

import com.slicepoker.zps.project.Comprehensive.Pojo.ComprehensiveQuality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author Zps
 * @date 2019/4/16 16:20
 **/
public interface ComPrehensiveQualityRespority extends JpaRepository<ComprehensiveQuality,Long>, JpaSpecificationExecutor<ComprehensiveQuality> {

    ComprehensiveQuality findByStudentNumberAndDeletedIsFalse(Long studentNumber);
}
