package com.bobzip.CRUD.recipe.model.service;

import java.util.List;

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


}
