package com.xadevpos.demo.mapper;

import com.xadevpos.demo.model.Admin;
import com.xadevpos.demo.model.Member;

public interface MemberMapper {

    Member selectById(Long id);
}
