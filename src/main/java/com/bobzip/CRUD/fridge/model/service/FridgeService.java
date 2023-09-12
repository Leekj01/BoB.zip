package com.bobzip.CRUD.fridge.model.service;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import com.bobzip.CRUD.fridge.model.vo.Fridge;
import com.bobzip.CRUD.recipe.model.vo.RecipeSummary;

public interface FridgeService {

	List<Fridge> myFridge(String memberId);

	List<String> allIngredients();

	void addFridge(List<Fridge> ingredients);

	void deleteFridge(int rowNumber);

	List<RecipeSummary> searchRecipe(List<String> ingredients);


}
