package com.xadevpos.demo.service;

import com.xadevpos.demo.model.Permission;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

public interface PermissionService {

    /**
     * 添加权限
     * @param permission
     * @return
     */
    public int insert(Permission permission);

}
