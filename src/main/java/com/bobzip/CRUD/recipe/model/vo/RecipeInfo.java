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
	private String image;
}
