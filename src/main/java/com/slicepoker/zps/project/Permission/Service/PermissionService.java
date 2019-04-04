package com.slicepoker.zps.project.Permission.Service;

import com.slicepoker.zps.project.Permission.Pojo.Permission;
import com.slicepoker.zps.project.User.Pojo.Commes;
import org.springframework.data.domain.Pageable;

/**
 * @author Zps
 * @date 2019/4/4 15:06
 **/
public interface PermissionService {

    Commes add(Permission permission);

    Commes delete(Long id);

    Commes update(Permission permission);

    Commes findFuzzy(Permission permission, Pageable pageable);
}
