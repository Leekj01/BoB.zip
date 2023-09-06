package com.bobzip.CRUD.recipe.model.dao;

import java.util.List;

import com.bobzip.CRUD.recipe.model.Paging;
import com.bobzip.CRUD.recipe.model.vo.Ingredient;
import com.bobzip.CRUD.recipe.model.vo.RecipeInfo;
import com.bobzip.CRUD.recipe.model.vo.RecipeSummary;

public interface RecipeDAO {

	List<RecipeSummary> selectBoard(Paging paging);

	int countBoard();

	List<RecipeInfo> selectRecipeInfo(String recipeId);

	List<Ingredient> selectIngredients(String recipeId);

}
