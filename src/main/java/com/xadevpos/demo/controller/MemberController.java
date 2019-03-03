package com.xadevpos.demo.controller;

import com.xadevpos.demo.Util.HttpUtil;
import com.xadevpos.demo.model.Member;
import com.xadevpos.demo.param.CommonResult;
import com.xadevpos.demo.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private HttpUtil httpUtil;

    /**
     * 查看会员信息  对此方法进行拦截
     * @param id
     * @return
     */
    @RequestMapping(value = "/getInfoById/{id}", method = RequestMethod.GET)
    public Object getInfo(@PathVariable("id") Long id){

        Member info = memberService.getInfo(id);
        if(info == null){
            return new CommonResult().validateFailed("用户不存在");
        }
        return new CommonResult().success(info);
    }
}
