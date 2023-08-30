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

	@Override
	public void insertNewMember(Member member) {
		sqlSession.insert("mapper.member.insertNewMember",member);
	}

	@Override
	public String selectOverlappedID(String memberId) {
		String result = sqlSession.selectOne("mapper.member.selectOverlappedID",memberId);
		return result;
	}

	@Override
	public Member getOneMember(String memberId) {
		return sqlSession.selectOne("mapper.member.selectOneMember",memberId);
	}

	@Override
	public void updateMember(Member member) {
		sqlSession.update("mapper.member.updateMember",member);
	}
	
	
	
	
}
