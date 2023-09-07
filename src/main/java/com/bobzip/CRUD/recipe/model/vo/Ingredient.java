package com.bobzip.CRUD.recipe.model.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ingredient {
	private int rowNumber;
	private String ingredientName;
	private int recipeId;
	
	public Ingredient (String ingredientName, int recipeId) {
		this.ingredientName = ingredientName;
		this.recipeId = recipeId;
	}
}
