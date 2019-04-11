package com.slicepoker.zps.project.Permission.Controller;

import com.slicepoker.zps.project.Permission.Pojo.UserRoleContact;
import com.slicepoker.zps.project.Permission.Respority.UserRoleContactRespority;
import com.slicepoker.zps.project.Permission.Service.UserRoleContactService;
import com.slicepoker.zps.project.User.Pojo.Commes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

/**
 * @author Zps
 * @date 2019/4/4 15:13
 **/
@RestController
@RequestMapping("/userRole")
public class UserRoleContactController {

    @Autowired
    private UserRoleContactService userRoleContactService;

    @Autowired
    private UserRoleContactRespority userRoleContactRespority;

    @GetMapping("/findByStudentNumber")
    public Commes findByStudentNumber(UserRoleContact userRoleContact){
        return userRoleContactService.findByStudentNumber(userRoleContact.getStudentNumber(),userRoleContact.getUserName());
    }

    @GetMapping("/delete")
    public Commes delete(Long id){
        return userRoleContactService.delete(id);
    }


    @PostMapping("/add/{studentNumber}/{userName}")
    @Transactional(rollbackOn = Exception.class)
    public Commes add(@PathVariable(name="studentNumber") Long studentNumber,
                      @PathVariable(name="userName") String userName,
                      @RequestBody List<UserRoleContact> params){
        Boolean success = true;
        Commes commes;
        deleteUserRoleContact(studentNumber, userName);
        if (params.size()>0){
            for (UserRoleContact userRoleContact:params){
                if (success){
                    commes = userRoleContactService.add(studentNumber,userName,userRoleContact.getRoleCode());
                    if (!Objects.equals(commes.getCode(),"200"))
                        success = false;
                }else {
                    break;
                }
            }
        }else {
            success = true;
        }
        return success ? Commes.successMes() : Commes.errorMes("401","保存失败");
    }


    private void deleteUserRoleContact(Long studentNumber,String userName){
        List<UserRoleContact> list = userRoleContactRespority.findByStudentNumberAndUserNameAndDeletedIsFalse(studentNumber,userName);
        if (list.size()>0){
            for (UserRoleContact userRoleContact:list) {
                userRoleContact.setDeleted(true);
                userRoleContactRespority.save(userRoleContact);
            }
        }
    }
}
