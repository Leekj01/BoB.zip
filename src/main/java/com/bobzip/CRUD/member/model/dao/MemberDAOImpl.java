package com.bobzip.CRUD.member.model.dao;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bobzip.CRUD.member.model.vo.Member;

@Repository("memberDAO")
public class MemberDAOImpl implements MemberDAO {
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public Member login(Map<String,String> loginMap) {
		return (Member)sqlSession.selectOne("mapper.member.login",loginMap);
	}
	
	
	
	
}
