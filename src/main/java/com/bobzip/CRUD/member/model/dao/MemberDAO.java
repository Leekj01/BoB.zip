package com.bobzip.CRUD.member.model.dao;

import java.util.Map;

import com.bobzip.CRUD.member.model.vo.Member;

public interface MemberDAO {
	
	public Member login(Map<String,String> loginMap);

	public void insertNewMember(Member member);

	public String selectOverlappedID(String memberId);

}
