package com.wq.member.service;

import com.wq.member.dto.MemberParam;
import com.wq.member.model.Member;

import java.util.List;

public interface MemberService {
    List<Member> list();

    Member getMember(Long id);

    int createMember(MemberParam memberParam);

    int updateMember(Long id, MemberParam memberParam);
}
