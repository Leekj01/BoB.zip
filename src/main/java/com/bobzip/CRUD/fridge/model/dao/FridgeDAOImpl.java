package com.bobzip.CRUD.fridge.model.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bobzip.CRUD.fridge.model.vo.Fridge;

@Repository("fridgeDAO")
public class FridgeDAOImpl implements FridgeDAO {
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<Fridge> selectMyFridge(String memberId) {
		return sqlSession.selectList("mapper.fridge.selectMyFridge",memberId);
	}

	@Override
	public List<String> selectAllIngredients() {
		return sqlSession.selectList("mapper.fridge.selectAllIngredients");
	}
	
	
}
