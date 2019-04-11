package com.slicepoker.zps.project.Permission.Service;

import com.slicepoker.zps.project.Permission.Pojo.Role;
import com.slicepoker.zps.project.User.Pojo.Commes;
import org.springframework.data.domain.Pageable;

/**
 * @author Zps
 * @date 2019/4/4 15:03
 **/
public interface RoleService {

    Commes add(Role role);

    Commes delete(Long id);

    Commes findFuzzy(Role role, Pageable pageable);

    Commes update(Role role);

    Commes findAll();
}
