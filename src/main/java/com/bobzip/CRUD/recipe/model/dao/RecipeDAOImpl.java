package com.bobzip.CRUD.recipe.model.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bobzip.CRUD.recipe.model.Paging;
import com.bobzip.CRUD.recipe.model.vo.Ingredient;
import com.bobzip.CRUD.recipe.model.vo.RecipeInfo;
import com.bobzip.CRUD.recipe.model.vo.RecipeSummary;

@Repository("recipeDAO")
public class RecipeDAOImpl implements RecipeDAO{
	
	@Autowired
	SqlSession sqlSession;

	@Override
	public List<RecipeSummary> selectBoard(Paging paging) {
		return sqlSession.selectList("mapper.recipe.selectRecipeSummaryBoard",paging);
	}

	@Override
	public int countBoard() {
		return sqlSession.selectOne("mapper.recipe.countRecipeSummary");
	}

	@Override
	public List<RecipeInfo> selectRecipeInfo(String recipeId) {
		return sqlSession.selectList("mapper.recipe.selectRecipeInfo",recipeId);
	}

	@Override
	public List<Ingredient> selectIngredients(String recipeId) {
		return sqlSession.selectList("mapper.recipe.selectIngredients",recipeId);
	}

	@Override
	public void insertRecipeSummary(RecipeSummary recipeSummary) {
		sqlSession.insert("mapper.recipe.insertRecipeSummary",recipeSummary);
	}

	@Override
	public int getRecipeId() {
		return sqlSession.selectOne("mapper.recipe.getMaxRecipeId");
	}

	@Override
	public void insertIngredient(Ingredient ingredient_) {
		sqlSession.insert("mapper.recipe.insertIngredient",ingredient_);
	}

	@Override
	public void insertRecipeInfo(RecipeInfo recipeInfo) {
		sqlSession.insert("mapper.recipe.insertRecipeInfo",recipeInfo);
	}

}
