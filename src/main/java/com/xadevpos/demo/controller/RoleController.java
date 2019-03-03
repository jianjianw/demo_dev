package com.xadevpos.demo.controller;


import com.xadevpos.demo.model.Role;
import com.xadevpos.demo.param.CommonResult;
import com.xadevpos.demo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    /**
     * 添加角色
     * @param role
     * @return
     */
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public Object insert(Role role) {
        int count = roleService.insert(role);
        if(count>0){
            return new CommonResult().success(count);
        }
        return new CommonResult().failed();
    }


}
