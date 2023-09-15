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
	
	public RecipeSummary (String recipeName, String summary, String nationName,
			String levelName, String typeName, String memberId, String image) {
		this.recipeName = recipeName;
		this.summary = summary;
		this.nationName = nationName;
		this.levelName = levelName;
		this.typeName = typeName;
		this.memberId = memberId;
		this.image = image;
	}
	
	public RecipeSummary(String recipeId, String image) {
		this.recipeId = Integer.parseInt(recipeId);
		this.image = image;
	}
}
