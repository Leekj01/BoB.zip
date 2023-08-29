package com.bobzip.CRUD.member.model.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bobzip.CRUD.member.model.dao.MemberDAO;
import com.bobzip.CRUD.member.model.vo.Member;



@Service("memberService")
@Transactional(propagation=Propagation.REQUIRED)
public class MemberServiceImpl implements MemberService {
	@Autowired
	private MemberDAO memberDAO;

	@Override
	public Member login(Map<String,String> loginMap) {
		return memberDAO.login(loginMap);
	}
	
	
}
