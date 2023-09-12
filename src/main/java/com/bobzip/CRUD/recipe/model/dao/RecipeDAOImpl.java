package com.bobzip.CRUD.recipe.model.dao;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bobzip.CRUD.recipe.model.Paging;
import com.bobzip.CRUD.recipe.model.vo.Ingredient;
import com.bobzip.CRUD.recipe.model.vo.RecipeComment;
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
	
	@Override
	public void insertComment(RecipeComment recipecomment) {
		sqlSession.insert("mapper.recipe.insertRecipeComment", recipecomment);
	}
	
	@Override
	public List<RecipeComment> recipecomments(int recipeId){
		return sqlSession.selectList("mapper.recipe.recipecomments", recipeId);
	}

	@Override
	public boolean recipecommentsDelete(int commentNo) {
		int affectedRows = sqlSession.delete("mapper.recipe.recipecommentsDelete", commentNo);
	    return affectedRows > 0;
	}

	@Override
	public boolean recipecommentsUpdate(int commentNo, String replyComment) {
		Map<String, Object> recipecommentsUpdate = new HashMap<String, Object>();
		recipecommentsUpdate.put("commentNo", commentNo);
		recipecommentsUpdate.put("replyComment", replyComment);

	    return sqlSession.update("mapper.recipe.recipecommentsUpdate", recipecommentsUpdate) > 0;
	}

	public RecipeSummary selectSummary(String recipeId) {
		return sqlSession.selectOne("mapper.recipe.selectSummary", recipeId);
	}

}
