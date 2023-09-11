package com.bobzip.CRUD.recipe.model.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RecipeComment {
	private int commentNo;
	private int recipeId;
	private String memberId;
	private String memberNick;
	private String replyComment;
}
