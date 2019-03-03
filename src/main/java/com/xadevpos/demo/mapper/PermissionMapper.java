package com.xadevpos.demo.mapper;

import com.xadevpos.demo.model.Permission;

import java.util.List;

public interface PermissionMapper {


    /**
     * 添加权限
     * @param permission
     * @return
     */
    public int insert(Permission permission);

    /**
     * 根据用户获取权限
     * @param adminId
     * @return
     */
    List<Permission> getPermissionList(Long adminId);

    /**
     * 根据用户可以访问的url
     * @param adminId
     * @return
     */
    List<String> getURLList(Long adminId);
}
