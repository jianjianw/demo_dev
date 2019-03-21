package com.xadevpos.demo.controller;


import com.xadevpos.demo.Util.Captcha;
import com.xadevpos.demo.Util.DateUtil;
import com.xadevpos.demo.model.Admin;
import com.xadevpos.demo.model.Permission;
import com.xadevpos.demo.param.AdminParam;
import com.xadevpos.demo.param.AdminRoleParam;
import com.xadevpos.demo.param.CommonResult;
import com.xadevpos.demo.service.AdminService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Value("${jwt.tokenHeader}")
    private String tokenHead;

    private final Map<String,String> concurrentHashMap = new ConcurrentHashMap<>();

    /**
     * 注册
     * @param adminParam
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Object register(AdminParam adminParam) {
        Admin admin = adminService.register(adminParam);
        if(admin == null){
            new CommonResult().failed();
        }
        return new CommonResult().success(admin);
    }

    /**
     * 登录
     * @param adminParam
     * @return
     */
    @RequestMapping(value = "/login")
    public Object login (AdminParam adminParam){
        String token = adminService.login(adminParam);
        if(token == null){
            return new CommonResult().validateFailed("用户名或者密码错误");
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return new CommonResult().success(tokenMap);
    }

    /**
     * 给用户分配角色
     * @return
     */
    @RequestMapping(value = "/role/update",method = RequestMethod.POST)
    public Object updateRole(@RequestBody AdminRoleParam adminRoleParam){

        int count = adminService.updateRole(adminRoleParam.getAdminId(),adminRoleParam.getRoleIds());
        if(count>=0){
            return new CommonResult().success(count);
        }
        return new CommonResult().failed();
    }

    /**
     * 根据用户id获取权限
     * @param adminId
     * @return
     */
    @RequestMapping(value = "/permission/{adminId}",method = RequestMethod.GET)
    @ResponseBody
    public Object getPermissionList(@PathVariable Long adminId){
        List<Permission> permissionList = adminService.getPermissionList(adminId);
        return new CommonResult().success(permissionList);
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public Object insert(Admin admin) {
        return adminService.insert(admin);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public Object delete(@PathVariable("id") Long id) {
        return adminService.delete(id);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Object update(Admin admin) {
        return adminService.update(admin);
    }

    @RequestMapping(value = "/selectById/{id}", method = RequestMethod.GET)
    public Object selectById(@PathVariable("id") Long id) {
        return adminService.selectById(id);
    }

    @RequestMapping(value = "/error")
    public Object return_ (HttpServletRequest request,HttpServletResponse response){
        String message = (String)request.getAttribute("message");
        return new CommonResult().validateFailed(message);
    }

    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public Object find(){
        return adminService.find();
    }


    /**
     * 用于生成带四位数字验证码的图片
     */
    @RequestMapping(value = "/getCaptcha")
    @ResponseBody
    public String imagecode(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");
        //唯一凭证
        String uuid = DateUtil.getUUID();
        response.setHeader("uuid",uuid);

        OutputStream os = response.getOutputStream();
        //返回验证码和图片的map
        Map<String,Object> map = Captcha.getImageCode(86, 37, os);
        //将验证码存放到map中
        String captcha = (String)map.get("strEnsure");
        concurrentHashMap.put(uuid,captcha);

        try {
            ImageIO.write((BufferedImage) map.get("image"), "jpg", os);
        } catch (IOException e) {
            return "";
        }   finally {
            if (os != null) {
                os.flush();
                os.close();
            }
        }
        return null;
    }
    @RequestMapping(value = "/nimei")
    @ResponseBody
    public String nimei(HttpServletRequest request, HttpServletResponse response) {
        return "nimei";
    }


    /**
     * 前端用户输入返回的验证码
     * @throws Exception
     */
    @RequestMapping(value = "/verify_login", method = RequestMethod.POST)
    public Object checkcode(AdminParam adminParam){

        String captcha = concurrentHashMap.get(adminParam.getUuid());
        if(captcha == null){
            return new CommonResult().validateFailed("验证码错误，请重新输入");
        }
        if (StringUtils.isEmpty(adminParam.getCheckCode()) || !(captcha.equalsIgnoreCase(adminParam.getCheckCode()))) {
           return new CommonResult().validateFailed("验证码错误，请重新输入");
        } else {
            return login(adminParam);
        }
    }


}
