package com.slicepoker.zps.project.Permission.Service.Impl;

import com.slicepoker.zps.project.Permission.Pojo.Role;
import com.slicepoker.zps.project.Permission.Pojo.UserRoleContact;
import com.slicepoker.zps.project.Permission.Respority.RolePermissionRespority;
import com.slicepoker.zps.project.Permission.Respority.RoleRespority;
import com.slicepoker.zps.project.Permission.Respority.UserRoleContactRespority;
import com.slicepoker.zps.project.Permission.Service.UserRoleContactService;
import com.slicepoker.zps.project.User.Pojo.Commes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Zps
 * @date 2019/4/4 15:14
 **/
@Service
public class UserRoleContactServiceImpl implements UserRoleContactService {

    @Autowired
    private UserRoleContactRespority userRoleContactRespority;

    @Autowired
    private RolePermissionRespority rolePermissionRespority;

    @Autowired
    private RoleRespority roleRespority;


    /**
     * @param id
     * @description 删除
     * **/
    @Override
    public Commes delete(Long id) {
        try {
            UserRoleContact userRoleContact = userRoleContactRespority.findByIdAndDeletedIsFalse(id);
            if (userRoleContact!=null){
                userRoleContact.setDeleted(true);
                userRoleContactRespority.save(userRoleContact);
                return Commes.successMes();
            }else {
                return Commes.errorMes("401","该实体已删除");
            }
        }catch (Exception e){
            e.printStackTrace();
            return Commes.badRequestError();
        }
    }


    /**
     * @param studentNumber
     * @description 查找该学号下所有角色
     * **/
    @Override
    public Commes findByStudentNumber(Long studentNumber,String userName) {
        try {
            List<UserRoleContact> list = userRoleContactRespority.findByStudentNumberAndUserNameAndDeletedIsFalse(studentNumber,userName);
            if (list.size()>0){
                return Commes.success(list);
            }else {
                return Commes.errorMes("401","没有数据");
            }
        }catch (Exception e){
            e.printStackTrace();
            return Commes.badRequestError();
        }
    }

    /**
     * @param studentNumber
     * @param userName
     * @param roleCode
     * @description 新增
     * **/
    @Override
    public Commes add(Long studentNumber,String userName,String roleCode) {
        try {
                UserRoleContact userRoleContact = new UserRoleContact();
                        userRoleContact.setRoleName(findRoleName(roleCode));
                        userRoleContact.setRoleCode(roleCode);
                        userRoleContact.setStudentNumber(studentNumber);
                        userRoleContact.setUserName(userName);
                        userRoleContactRespority.save(userRoleContact);
                        return Commes.successMes();
        }catch (Exception e){
            e.printStackTrace();
            return Commes.badRequestError();
        }
    }



    private String findRoleName(String roleCode){
        Role role = roleRespority.findByRoleCodeAndDeletedIsFalse(roleCode);
        if (role!=null){
            return role.getRoleName();
        }else {
            return null;
        }
    }


    public void deleteUserRoleContact(Long studentNumber,String userName){
        List<UserRoleContact> list = userRoleContactRespority.findByStudentNumberAndUserNameAndDeletedIsFalse(studentNumber, userName);
        if (list.size()>0){
            for (UserRoleContact userRoleContact:list) {
                userRoleContact.setDeleted(true);
                userRoleContactRespority.save(userRoleContact);
            }
        }
    }






}
