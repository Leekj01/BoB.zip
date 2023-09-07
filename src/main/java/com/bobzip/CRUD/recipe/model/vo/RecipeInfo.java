package com.bobzip.CRUD.recipe.model.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RecipeInfo {
	private int rowNumber;
	private int recipeId;
	private int cookingNumber;
	private String cookingStep;
	
	public RecipeInfo (int recipeId, int cookingNumber,String cookingStep) {
		this.recipeId = recipeId;
		this.cookingNumber = cookingNumber;
		this.cookingStep = cookingStep;
	}
}
