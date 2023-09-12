package com.bobzip.CRUD.fridge.model.dao;

import java.util.List;
import java.util.Map;

import com.bobzip.CRUD.fridge.model.vo.Fridge;
import com.bobzip.CRUD.recipe.model.vo.RecipeSummary;

public interface FridgeDAO {

	List<Fridge> selectMyFridge(String memberId);

	List<String> selectAllIngredients();

	void insertMyFridge(List<Fridge> ingredients);

	void deleteMyFridge(int rowNumber);

	List<RecipeSummary> searchRecipe(List<String> ingredients);

}
