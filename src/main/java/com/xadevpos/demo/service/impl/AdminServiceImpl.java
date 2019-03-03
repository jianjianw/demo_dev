package com.xadevpos.demo.service.impl;

import com.xadevpos.demo.Util.JwtTokenUtil;
import com.xadevpos.demo.mapper.AdminMapper;
import com.xadevpos.demo.mapper.PermissionMapper;
import com.xadevpos.demo.mapper.RoleMapper;
import com.xadevpos.demo.model.Admin;
import com.xadevpos.demo.model.AdminRoleRelation;
import com.xadevpos.demo.model.Permission;
import com.xadevpos.demo.param.AdminParam;
import com.xadevpos.demo.service.AdminService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

@Service
public class AdminServiceImpl implements AdminService {


    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private PermissionMapper permissionMapper;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private RoleMapper roleMapper;


    @Override
    public int insert(Admin admin) {
        return adminMapper.insert(admin);
    }

    @Override
    public int delete(Long id) {
        return adminMapper.delete(id);
    }

    @Override
    public int update(Admin admin) {
        return adminMapper.update(admin);
    }

    @Override
    public Admin selectById(Long id) {
        return adminMapper.selectById(id);
    }

    @Override
    public Admin register(AdminParam adminParam) {

        Admin dbAdmin = adminMapper.selectByUsername(adminParam.getUsername());
        Admin admin = null;
        if(dbAdmin != null){
            return null;
        }else{
            admin = new Admin();
            BeanUtils.copyProperties(adminParam, admin);
            admin.setCreateTime(new Date());
            admin.setStatus(1);
            adminMapper.insert(admin);
        }
        return admin;
    }

    @Override
    public List<Admin> find() {
        return adminMapper.find();
    }

    @Override
    public String login(AdminParam adminParam) {
        String token = null;

        Admin admin = adminMapper.selectByUsernameAndPassword(adminParam);
        if (admin != null) {
            //写令牌
            Map<String, Object> map = new HashMap<>();
            map.put("id", admin.getId());
            map.put("username", admin.getUsername());
            token = jwtTokenUtil.generateToken(map);
        }
        return token;
    }

    @Override
    public List<Permission> getPermissionList(Long adminId) {
        return permissionMapper.getPermissionList(adminId);
    }

    @Override
    public List<String> getURLList(Long adminId) {
        return permissionMapper.getURLList(adminId);
    }

    @Override
    public int updateRole(Long adminId, List<Long> roleIds) {
        int count = roleIds == null ? 0 : roleIds.size();
        //先删除原来的关系
        roleMapper.deleteByAdminId(adminId);
        //建立新关系
        if (!CollectionUtils.isEmpty(roleIds)) {
            List<AdminRoleRelation> list = new ArrayList<>();
            for (Long roleId : roleIds) {
                AdminRoleRelation roleRelation = new AdminRoleRelation();
                roleRelation.setAdminId(adminId);
                roleRelation.setRoleId(roleId);
                list.add(roleRelation);
            }
            roleMapper.insertList(list);
        }
        return count;
    }
}
