package com.xadevpos.demo.service;

import com.xadevpos.demo.model.Member;
import com.xadevpos.demo.param.CommonResult;
import org.springframework.web.bind.annotation.PathVariable;

public interface MemberService {


    public Member getInfo(Long id);
}
