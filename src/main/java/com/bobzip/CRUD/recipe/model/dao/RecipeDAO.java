package com.bobzip.CRUD.recipe.model.dao;

import java.util.List;

import com.bobzip.CRUD.recipe.model.Paging;
import com.bobzip.CRUD.recipe.model.vo.RecipeSummary;

public interface RecipeDAO {

	List<RecipeSummary> selectBoard(Paging paging);

	int countBoard();

}
