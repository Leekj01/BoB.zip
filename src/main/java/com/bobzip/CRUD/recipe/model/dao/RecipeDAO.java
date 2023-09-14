package com.bobzip.CRUD.recipe.model.dao;

import java.util.List;
import java.util.Map;

import com.bobzip.CRUD.recipe.model.Paging;
import com.bobzip.CRUD.recipe.model.vo.Ingredient;
import com.bobzip.CRUD.recipe.model.vo.RecipeComment;
import com.bobzip.CRUD.recipe.model.vo.RecipeInfo;
import com.bobzip.CRUD.recipe.model.vo.RecipeSummary;

public interface RecipeDAO {

	List<RecipeSummary> selectBoard(Paging paging);

	int countBoard();

	List<RecipeInfo> selectRecipeInfo(String recipeId);

	List<Ingredient> selectIngredients(String recipeId);

	void insertRecipeSummary(RecipeSummary recipeSummary);

	int getRecipeId();

	void insertIngredient(Ingredient ingredient_);

	void insertRecipeInfo(RecipeInfo recipeInfo);

	void insertComment(RecipeComment recipecomment);
	
	List<RecipeComment> recipecomments(int recipeId);

	boolean recipecommentsDelete(int commentNo);

	boolean recipecommentsUpdate(int commentNo, String replyComment);
  
	RecipeSummary selectSummary(String recipeId);
	
	int selectMyrecipeCount(String memberId);

	List<RecipeSummary> selectAllMyrecipe(Map<String, Object> parameter);

	RecipeSummary selectUpdateRecipe(String recipeId);

	boolean myrecipeImageDelete(String image);

	List<Ingredient> updateMyRecipeIngredient(String recipeId);

	List<RecipeInfo> updateMyRecipeInfo(String recipeId);

	void updateMyRecipeSummary(RecipeSummary recipeSummary);

	boolean deleteMyRecipe(String recipeId);

	
}
