package com.xadevpos.demo.service.impl;

import com.xadevpos.demo.mapper.PermissionMapper;
import com.xadevpos.demo.model.Permission;
import com.xadevpos.demo.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public int insert(Permission permission) {
        permission.setStatus(1);
        permission.setCreateTime(new Date());
        permission.setSort(0);
        return permissionMapper.insert(permission);
    }
}
