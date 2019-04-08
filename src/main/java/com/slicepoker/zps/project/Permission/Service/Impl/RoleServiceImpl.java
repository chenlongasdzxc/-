package com.slicepoker.zps.project.Permission.Service.Impl;

import com.slicepoker.zps.project.Permission.Pojo.Role;
import com.slicepoker.zps.project.Permission.Respority.RoleRespority;
import com.slicepoker.zps.project.Permission.Service.RoleService;
import com.slicepoker.zps.project.User.Pojo.Commes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Zps
 * @date 2019/4/4 15:03
 **/
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRespority roleRespority;

    /**
     * @param id
     * @description 删除
     * **/
    @Override
    public Commes delete(Long id) {
        try {
            Role role = roleRespority.findByIdAndDeletedIsFalse(id);
            if (role!=null){
                role.setDeleted(true);
                roleRespority.saveAndFlush(role);
                return Commes.successMes();
            }else {
                return Commes.errorMes("401","改实体不存在");
            }
        }catch (Exception e){
            e.printStackTrace();
            return Commes.badRequestError();
        }
    }


    /**
     * @param role
     * @description 新增
     * **/
    @Override
    public Commes add(Role role) {
       try {
           Role role1 = roleRespority.findByRoleNameAndDeletedIsFalse(role.getRoleName());
           if (role1==null){
               roleRespority.save(role);
                return Commes.successMes();
           }else {
               return Commes.errorMes("401","该实体已存在");
           }
       }catch (Exception e){
           e.printStackTrace();
           return Commes.badRequestError();
       }
    }


    /**
     * @param role
     * @param pageable
     * @description 模糊查询
     * **/
    @Override
    public Commes findFuzzy(Role role, Pageable pageable) {
       try {
           Page<Role> page = roleRespority.findAll(((root, query, cb) -> {
               List<Predicate> list = new ArrayList<>();
               list.add(cb.equal(root.get("deleted"),false));
               if (role.getRoleName()!=null && !"".equals(role.getRoleName())){
                   list.add(cb.equal(root.get("roleName"),role.getRoleName()));
               }
               if (role.getRoleCode()!=null && !"".equals(role.getRoleCode())){
                   list.add(cb.equal(root.get("roleCode"),role.getRoleCode()));
               }
               return cb.and(list.toArray(new Predicate[list.size()]));
           }),pageable);
           return Commes.success(page);
       }catch (Exception e){
           e.printStackTrace();
           return Commes.badRequestError();
       }
    }

    /**
     * @param role
     * @description 编辑
     * **/
    @Override
    public Commes update(Role role) {
        try {
            Role role1 = roleRespority.findByRoleNameAndDeletedIsFalse(role.getRoleName());
            if (role1!=null){
                role1.setRoleCode(role.getRoleCode());
                role1.setRoleName(role.getRoleName());
                roleRespority.saveAndFlush(role1);
                return Commes.successMes();
            }else {
                return Commes.errorMes("401","已存在该实体");
            }
        }catch (Exception e){
            e.printStackTrace();
            return Commes.badRequestError();
        }
    }
}
