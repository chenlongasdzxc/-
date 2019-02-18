package com.slicepoker.zps.project.Checking.Service;

import com.slicepoker.zps.project.Checking.Pojo.AskForLeave;
import com.slicepoker.zps.project.User.Pojo.Commes;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Zps
 * @date 2019/2/15 17:33
 **/
public interface LeavingService {

    Commes update(AskForLeave askForLeave);

    Commes findLeaving(AskForLeave askForLeave,Pageable pageable);

    Commes findFuzzy(AskForLeave askForLeave,Pageable pageable);

    Commes delete(Long id);

    Commes findPersonalData(Long studentNumber,Pageable pageable);
}
