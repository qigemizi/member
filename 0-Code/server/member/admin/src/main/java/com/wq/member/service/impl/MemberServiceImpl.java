package com.wq.member.service.impl;

import com.wq.member.mapper.MemberMapper;
import com.wq.member.model.Member;
import com.wq.member.model.MemberExample;
import com.wq.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberMapper memberMapper;

    @Override
    public List<Member> list() {
        // 查询所有
        return memberMapper.selectByExample(new MemberExample());
    }
}
