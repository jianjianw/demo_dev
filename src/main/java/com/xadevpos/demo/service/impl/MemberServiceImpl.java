package com.xadevpos.demo.service.impl;

import com.xadevpos.demo.mapper.MemberMapper;
import com.xadevpos.demo.model.Member;
import com.xadevpos.demo.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberMapper memberMapper;

    @Override
    public Member getInfo(Long id) {
        return memberMapper.selectById(id);
    }
}
