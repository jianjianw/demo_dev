package com.xadevpos.demo.mapper;

import com.xadevpos.demo.model.Admin;
import com.xadevpos.demo.model.Music;
import com.xadevpos.demo.model.Permission;
import com.xadevpos.demo.param.AdminParam;

import java.util.List;

public interface AdminMapper {


    int insert(Admin admin);

    int delete(Long id);

    int update(Admin admin);

    Admin selectById(Long id);

    Admin selectByUsername(String username);

    public List<Admin> find();

    Admin selectByUsernameAndPassword(AdminParam adminParam);

    public List<Music> selectMusic();

    void insertMusic(List<Music> musicList);
}
