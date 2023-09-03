package com.bobzip.CRUD.fridge.model.service;

import java.sql.Date;
import java.util.List;

import com.bobzip.CRUD.fridge.model.vo.Fridge;

public interface FridgeService {

	List<Fridge> myFridge(String memberId);

	List<String> allIngredients();

	void addFridge(List<String> ingredients);


}
