package com.bobzip.CRUD.recipe.model.service;

import java.util.List;

import com.bobzip.CRUD.recipe.model.Paging;
import com.bobzip.CRUD.recipe.model.vo.RecipeSummary;

public interface RecipeService {

	List<RecipeSummary> selectBoard(Paging paging);

	int countBoard();

}
