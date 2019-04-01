package com.slicepoker.zps.project.StudentMoral.Controller;
import com.slicepoker.zps.project.Student.Pojo.StudentMoral;
import com.slicepoker.zps.project.StudentMoral.Pojo.StudentMoralOut;
import com.slicepoker.zps.project.StudentMoral.Service.StudentMoralOutService;
import com.slicepoker.zps.project.User.Pojo.Commes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

/**
 * @author Zps
 * @date 2019/3/14 8:55
 **/
@RestController
@RequestMapping("/studentMoralOut")
public class StudentMoralOutController {

    @Autowired
    private StudentMoralOutService studentMoralOutService;

    @PostMapping("/add")
    public Commes add(@RequestBody StudentMoralOut studentMoralOut){
        return studentMoralOutService.add(studentMoralOut);
    }

    @GetMapping("/delete")
    public Commes delete(Long id){
        return studentMoralOutService.delete(id);
    }

    @GetMapping("/setStates")
    public Commes setStates(StudentMoralOut studentMoralOut){
        return studentMoralOutService.setStates(studentMoralOut);
    }

    @GetMapping("/findPersonal")
    public Commes findPersonalMoralOut(StudentMoralOut studentMoralOut, Pageable pageable){
        return studentMoralOutService.findPersonalMoralOut(studentMoralOut,pageable);
    }

    @GetMapping("/findFuzzy")
    public Commes findFuzzy(StudentMoralOut studentMoralOut,Pageable pageable){
        return studentMoralOutService.findFuzzy(studentMoralOut, pageable);
    }

    @PostMapping("/update")
    public Commes update(@RequestBody StudentMoralOut studentMoralOut){
        return studentMoralOutService.update(studentMoralOut);
    }
}
