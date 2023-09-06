package com.bobzip.CRUD.recipe.model.service;

import java.util.List;

import com.bobzip.CRUD.recipe.model.Paging;
import com.bobzip.CRUD.recipe.model.vo.Ingredient;
import com.bobzip.CRUD.recipe.model.vo.RecipeInfo;
import com.bobzip.CRUD.recipe.model.vo.RecipeSummary;

public interface RecipeService {

	List<RecipeSummary> selectBoard(Paging paging);

	int countBoard();

	List<RecipeInfo> selectRecipeInfo(String recipeId);

	List<Ingredient> selectIngredients(String recipeId);

}
