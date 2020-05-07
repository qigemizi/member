package com.wq.member.service.impl;

import cn.hutool.core.convert.Convert;
import com.wq.member.dto.MemberParam;
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

    @Override
    public Member getMember(Long id) {
        return memberMapper.selectByPrimaryKey(Convert.toInt(id));
    }

    @Override
    public int createMember(MemberParam memberParam) {
        Member member =new Member();
        member.setName(memberParam.getName());
        member.setPhone(memberParam.getPhone());
        member.setRegisterData(memberParam.getRegisterDate());
        return memberMapper.insert(member);
    }

    @Override
    public int updateMember(Long id, MemberParam memberParam) {
        Member member =new Member();
        member.setId(Convert.toInt(id));
        member.setName(memberParam.getName());
        member.setPhone(memberParam.getPhone());
        member.setRegisterData(memberParam.getRegisterDate());
        return memberMapper.updateByPrimaryKey(member);
    }
}
