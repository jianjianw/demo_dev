package com.xadevpos.demo.controller;


import com.xadevpos.demo.model.Permission;
import com.xadevpos.demo.param.CommonResult;
import com.xadevpos.demo.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    /**
     * 添加权限
     * @param permission
     * @return
     */
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public Object insert(Permission permission) {
        int count = permissionService.insert(permission);
        if(count>0){
            return new CommonResult().success(count);
        }
        return new CommonResult().failed();
    }
}
