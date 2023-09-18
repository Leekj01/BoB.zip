package com.bobzip.CRUD.recipe.model.service;

import java.util.List;
import java.util.Map;

import com.bobzip.CRUD.recipe.model.Paging;
import com.bobzip.CRUD.recipe.model.vo.Ingredient;
import com.bobzip.CRUD.recipe.model.vo.RecipeComment;
import com.bobzip.CRUD.recipe.model.vo.RecipeInfo;
import com.bobzip.CRUD.recipe.model.vo.RecipeSummary;

public interface RecipeService {

	List<RecipeSummary> selectBoard(Paging paging);

	int countBoard();

	List<RecipeInfo> selectRecipeInfo(String recipeId);

	List<Ingredient> selectIngredients(String recipeId);

	void insertRecipeSummary(RecipeSummary recipeSummary);

	int getRecipeId();

	void insertIngredient(Ingredient ingredient_);

	void insertRecipeInfo(RecipeInfo recipeInfo);

	void insertComment(RecipeComment recipecomment);

	List<RecipeComment> getCommentsByRecipeId(int recipeId);

	boolean deleteComment(int commentNo);

	boolean editComment(int commentNo, String replyComment);

	RecipeSummary selectSummary(String recipeId);
	
	int myrecipecountBoard(String memberId);

	List<RecipeSummary> myrecipeResult(Map<String, Object> parameter);

	RecipeSummary updateMyRecipe(String recipeId);

	boolean myrecipeImageDelete(RecipeSummary recipeSummary);

	List<Ingredient> updateMyRecipeIngredient(String recipeId);

	List<RecipeInfo> updateMyRecipeInfo(String recipeId);

	void updateMyRecipeSummary(RecipeSummary recipeSummary);

	boolean deleteMyRecipeSummary(String recipeId);

	void updateMyRecipeIngredients(Ingredient ingredient_);

	void deleteMyRecipeInfo(String recipeId);

	void deleteMyRecipeIngredient(String recipeId);

	

	List<RecipeSummary> selectSearchResult(Map parameter);

	int countSearchResult(String inputedRecipeName);

	boolean editComment(RecipeComment recipeComment);

}
