package com.bobzip.CRUD.recipe.model.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RecipeSummary {
	private int recipeId;
	private String recipeName;
	private String summary;
	private String nationName;
	private String levelName;
	private String typeName;
	private String memberId;
	private String image;
}
