package com.bobzip.CRUD.fridge.model.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bobzip.CRUD.fridge.model.vo.Fridge;
import com.bobzip.CRUD.recipe.model.vo.RecipeSummary;

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
	public void deleteMyFridge(int rowNumber) {
		sqlSession.delete("mapper.fridge.deleteMyFridge", rowNumber);
	}

	@Override
	public List<RecipeSummary> searchRecipe( List<String> ingredients) {
		List<RecipeSummary> searchingResult = new ArrayList<RecipeSummary>();
		for (String ingredientName : ingredients) {
			List<Integer> recipeIds = sqlSession.selectList("mapper.fridge.searchRecipeId", ingredientName);
			for (int recipeId : recipeIds) {
				RecipeSummary summary = sqlSession.selectOne("mapper.fridge.searchRecipeSummary", recipeId);
				searchingResult.add(summary);
			}
		}
		return searchingResult;
	}
	
	
}
