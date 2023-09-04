package com.bobzip.CRUD.fridge.model.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bobzip.CRUD.fridge.model.dao.FridgeDAO;
import com.bobzip.CRUD.fridge.model.vo.Fridge;

@Service("fridgeService")
public class FridgeServiceImpl implements FridgeService {

	@Autowired
	private FridgeDAO fridgeDAO;

	@Override
	public List<Fridge> myFridge(String memberId) {
		return fridgeDAO.selectMyFridge(memberId);
	}

	@Override
	public List<String> allIngredients() {	
		return fridgeDAO.selectAllIngredients();
	}

	@Override
	public void addFridge(List<Fridge> ingredients) {
		fridgeDAO.insertMyFridge(ingredients);
	}

	@Override
	public void deleteFridge(int fridgeNumber) {
		fridgeDAO.deleteMyFridge(fridgeNumber);
	}
	
}
