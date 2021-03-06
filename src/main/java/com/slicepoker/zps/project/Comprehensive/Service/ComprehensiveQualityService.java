package com.slicepoker.zps.project.Comprehensive.Service;

import com.slicepoker.zps.project.Comprehensive.Pojo.ComprehensiveQuality;
import com.slicepoker.zps.project.User.Pojo.Commes;
import org.springframework.data.domain.Pageable;

/**
 * @author Zps
 * @date 2019/4/16 16:20
 **/
public interface ComprehensiveQualityService {

    Commes update(ComprehensiveQuality comprehensiveQuality);

    Commes findFuzzy(ComprehensiveQuality comprehensiveQuality, Pageable pageable);

    Commes findPersonal(Long studentNumber);

    Commes setStates(Long id,String states);

}
