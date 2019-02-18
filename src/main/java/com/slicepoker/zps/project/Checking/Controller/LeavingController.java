package com.slicepoker.zps.project.Checking.Controller;

import com.slicepoker.zps.project.Checking.Pojo.AskForLeave;
import com.slicepoker.zps.project.Checking.Service.LeavingService;
import com.slicepoker.zps.project.User.Pojo.Commes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

/**
 * @author Zps
 * @date 2019/2/15 17:31
 **/
@RestController
@RequestMapping("/Leaving")
public class LeavingController {

    @Autowired
    private LeavingService leavingService;

    @PostMapping("/update")
    public Commes update(@RequestBody AskForLeave askForLeave){
        return leavingService.update(askForLeave);
    }

    @GetMapping("/find")
    public Commes findAll(AskForLeave askForLeave,Pageable pageable){
        return leavingService.findLeaving(askForLeave,pageable);
    }

    @GetMapping("/findFuzzy")
    public Commes findFuzzy(AskForLeave askForLeave,Pageable pageable){
        return leavingService.findFuzzy(askForLeave, pageable);
    }

    @GetMapping("/delete")
    public Commes delete(Long id){
        return leavingService.delete(id);
    }

    @GetMapping("/findPersonal")
    public Commes findPersonal(Long studentNumber,Pageable pageable){
        return leavingService.findPersonalData(studentNumber, pageable);
    }

}
