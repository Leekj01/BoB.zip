package com.bobzip.CRUD.member.model.service;

import java.util.Map;

import com.bobzip.CRUD.member.model.vo.Member;

public interface MemberService {

	public Member login(Map<String,String> loginMap);

	public void addMember(Member member);

	public String overlapped(String memberId);

	public Member getMemberInfo(String memberId);

	public void updateMember(Member member);

}
