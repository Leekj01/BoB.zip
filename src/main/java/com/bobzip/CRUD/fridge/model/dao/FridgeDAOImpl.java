package com.bobzip.CRUD.fridge.model.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

	@Override
	public void insertMyFridge(List<Fridge> ingredients) {
		for (Fridge ingredient : ingredients) {
			sqlSession.insert("mapper.fridge.insertMyFridge", ingredient);
		}
	}

	@Override
	public void deleteMyFridge(int fridgeNumber) {
		sqlSession.delete("mapper.fridge.deleteMyFridge", fridgeNumber);
	}
	
	
}
