package com.xadevpos.demo.service;

import com.xadevpos.demo.model.Admin;
import com.xadevpos.demo.model.Music;
import com.xadevpos.demo.model.Permission;
import com.xadevpos.demo.param.AdminParam;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AdminService {

    /**
     * 插入用户
     * @param admin
     * @return
     */
    int insert(Admin admin);

    /**
     * 删除用户
     * @param id
     * @return
     */
    int delete(Long id);

    /**
     * 更新用户
     * @param admin
     * @return
     */
    int update(Admin admin);

    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    Admin selectById(Long id);

    /**
     * 注册功能
     * @param adminParam
     * @return
     */
    public Admin register(AdminParam adminParam);



    public List<Admin> find();

    /**
     * 登录接口
     * @param adminParam
     * @return
     */
    String login(AdminParam adminParam);

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

    /**
     * 修改用户角色关系
     */
    @Transactional
    int updateRole(Long adminId, List<Long> roleIds);

    public List<Music> selectMusic();

    void insertMusic(List<Music> musicList);
}
