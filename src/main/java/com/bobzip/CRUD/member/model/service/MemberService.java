package com.bobzip.CRUD.member.model.service;

import java.util.Map;

import com.bobzip.CRUD.member.model.vo.Member;

public interface MemberService {

	public Member login(Map<String,String> loginMap);

}
