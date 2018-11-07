package com.slicepoker.zps.project.Service;

import com.slicepoker.zps.project.Pojo.Commes;
import org.springframework.data.domain.Pageable;

/**
 * @author Zps
 * @date 2018/10/26 9:52
 **/
public interface StudentInfoService {

    Commes findFuzzy(Long studentCode, String studentName, Integer sex, String studentClass, Pageable pageable);
}
