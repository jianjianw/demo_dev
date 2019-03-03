package com.xadevpos.demo.mapper;

import com.xadevpos.demo.model.AdminRoleRelation;
import com.xadevpos.demo.model.Role;

import java.util.List;

public interface RoleMapper {

    int insert(Role role);

    void deleteByAdminId(Long adminId);

    /**
     * 批量插入用户角色关系
     */
    void insertList(List<AdminRoleRelation> list);
}
