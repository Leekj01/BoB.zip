package com.bobzip.CRUD.recipe.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bobzip.CRUD.recipe.model.Paging;
import com.bobzip.CRUD.recipe.model.dao.RecipeDAO;
import com.bobzip.CRUD.recipe.model.vo.Ingredient;
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

}
