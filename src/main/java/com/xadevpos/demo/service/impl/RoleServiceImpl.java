package com.xadevpos.demo.service.impl;

import com.xadevpos.demo.mapper.RoleMapper;
import com.xadevpos.demo.model.Role;
import com.xadevpos.demo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public int insert(Role role) {
        role.setCreateTime(new Date());
        role.setStatus(1);
        role.setAdminCount(0);
        role.setSort(0);
        return roleMapper.insert(role);
    }


}
