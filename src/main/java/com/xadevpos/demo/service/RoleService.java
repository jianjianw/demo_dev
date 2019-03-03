package com.xadevpos.demo.service;

import com.xadevpos.demo.model.Role;
import com.xadevpos.demo.param.CommonResult;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface RoleService {

    /**
     * 添加角色
     * @param role
     * @return
     */
    int insert(Role role);


}
