package com.bobzip.CRUD.recipe.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bobzip.CRUD.recipe.model.Paging;
import com.bobzip.CRUD.recipe.model.dao.RecipeDAO;
import com.bobzip.CRUD.recipe.model.vo.Ingredient;
import com.bobzip.CRUD.recipe.model.vo.RecipeComment;
import com.bobzip.CRUD.recipe.model.vo.RecipeInfo;
import com.bobzip.CRUD.recipe.model.vo.RecipeSummary;

@Service("recipeService")
public class RecipeServiceImpl implements RecipeService{
	
	@Autowired
	RecipeDAO recipeDAO;

	@Override
	public List<RecipeSummary> selectBoard(Paging paging) {
		return recipeDAO.selectBoard(paging);
	}

	@Override
	public int countBoard() {
		return recipeDAO.countBoard();
	}

	@Override
	public List<RecipeInfo> selectRecipeInfo(String recipeId) {
		return recipeDAO.selectRecipeInfo(recipeId);
	}

	@Override
	public List<Ingredient> selectIngredients(String recipeId) {
		return recipeDAO.selectIngredients(recipeId);
	}

	@Override
	public void insertRecipeSummary(RecipeSummary recipeSummary) {
		recipeDAO.insertRecipeSummary(recipeSummary);
	}

	@Override
	public int getRecipeId() {
		return recipeDAO.getRecipeId();
	}

	@Override
	public void insertIngredient(Ingredient ingredient_) {
		recipeDAO.insertIngredient(ingredient_);
	}

	@Override
	public void insertRecipeInfo(RecipeInfo recipeInfo) {
		recipeDAO.insertRecipeInfo(recipeInfo);
	}
	
	@Override
	public void insertComment(RecipeComment recipecomment) {
		recipeDAO.insertComment(recipecomment);
	}
	
	@Override
	public List<RecipeComment> getCommentsByRecipeId(int recipeId){
		return recipeDAO.recipecomments(recipeId);
	}

	@Override
	public boolean deleteComment(int commentNo) {
		return recipeDAO.recipecommentsDelete(commentNo);
	}

	@Override
	public boolean editComment(int commentNo, String replyComment) {
		return recipeDAO.recipecommentsUpdate(commentNo, replyComment);
	}
	
	@Override
	public RecipeSummary selectSummary(String recipeId) {
		return recipeDAO.selectSummary(recipeId);
	}
	
	@Override
	public int myrecipecountBoard(String memberId) {
		return recipeDAO.selectMyrecipeCount(memberId);
	}

	@Override
	public List<RecipeSummary> myrecipeResult(Map<String, Object> parameter) {
		return recipeDAO.selectAllMyrecipe(parameter);
	}

	@Override
	public RecipeSummary updateMyRecipe(String recipeId) {
		return recipeDAO.selectUpdateRecipe(recipeId);
	}

	@Override
	public boolean myrecipeImageDelete(String image) {
		return recipeDAO.myrecipeImageDelete(image);
	}

	@Override
	public List<Ingredient> updateMyRecipeIngredient(String recipeId) {
		return recipeDAO.updateMyRecipeIngredient(recipeId);
	}

	@Override
	public List<RecipeInfo> updateMyRecipeInfo(String recipeId) {
		return recipeDAO.updateMyRecipeInfo(recipeId);
	}

	@Override
	public void updateMyRecipeSummary(RecipeSummary recipeSummary) {
		recipeDAO.updateMyRecipeSummary(recipeSummary);
		
	}

	@Override
	public boolean deleteMyRecipe(String recipeId) {
		 boolean delteMyRecipe = recipeDAO.deleteMyRecipe(recipeId);
		 return delteMyRecipe;
	}
	
	
}
