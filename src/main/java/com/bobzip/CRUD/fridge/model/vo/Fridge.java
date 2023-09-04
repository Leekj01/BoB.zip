package com.bobzip.CRUD.fridge.model.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Fridge {
	private int fridgeNumber;
	private String memberId;
	private String ingredientName;
}
