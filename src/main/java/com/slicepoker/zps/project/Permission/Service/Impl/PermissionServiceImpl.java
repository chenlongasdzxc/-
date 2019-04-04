package com.slicepoker.zps.project.Permission.Service.Impl;

import com.slicepoker.zps.project.Permission.Pojo.Permission;
import com.slicepoker.zps.project.Permission.Respority.PermissionRespority;
import com.slicepoker.zps.project.Permission.Service.PermissionService;
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
 * @date 2019/4/4 15:11
 **/
@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionRespority permissionRespority;


    /**
     * @param id
     * @description 删除
     * **/
    @Override
    public Commes delete(Long id) {
        try {
            Permission permission = permissionRespority.findByIdAndDeletedIsFalse(id);
            if (permission!=null){
                permission.setDeleted(true);
                permissionRespority.saveAndFlush(permission);
                return Commes.successMes();
            }else {
                return Commes.errorMes("","");
            }
        }catch (Exception e){
            e.printStackTrace();
            return Commes.badRequestError();
        }
    }

    /**
     * @param permission
     * @description 添加
     * **/
    @Override
    public Commes add(Permission permission) {
        try {
            Permission permission1 = permissionRespority.findByPermissionNameAndDeletedIsFalse(permission.getPermissionName());
            if (permission1==null){
                permissionRespority.saveAndFlush(permission);
                return Commes.successMes();
            }else {
                return Commes.errorMes("401","已存在该条数据");
            }
        }catch (Exception e){
            e.printStackTrace();
            return Commes.badRequestError();
        }
    }

    /**
     * @param permission
     * @param pageable
     * @description 模糊查询
     * **/
    @Override
    public Commes findFuzzy(Permission permission, Pageable pageable) {
        try {
            Page<Permission> page = permissionRespority.findAll(((root, query, cb) -> {
                List<Predicate> list = new ArrayList<>();
                list.add(cb.equal(root.get("deleted"),false));
                if (permission.getPermissionName()!=null && !"".equals(permission.getPermissionName())){
                    list.add(cb.like(root.get("permissionName"),permission.getPermissionName()));
                }
                if (permission.getPermissionCode()!=null && !"".equals(permission.getPermissionCode())){
                    list.add(cb.equal(root.get("permissionCode"),permission.getPermissionCode()));
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
     * @param permission
     * @description 编辑
     * **/
    @Override
    public Commes update(Permission permission) {
        try {
            Permission permission1 = permissionRespority.findByIdAndDeletedIsFalse(permission.getId());
            if (permission1!=null){
                permission1.setPermissionCode(permission.getPermissionCode());
                permission1.setPermissionName(permission.getPermissionName());
                permissionRespority.saveAndFlush(permission1);
                return Commes.successMes();
            }else {
                return Commes.errorMes("401","查询失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            return Commes.badRequestError();
        }
    }
}
