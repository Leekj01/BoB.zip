package com.bobzip.CRUD.fridge.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bobzip.CRUD.fridge.model.dao.FridgeDAO;
import com.bobzip.CRUD.fridge.model.vo.Fridge;

@Service("fridgeService")
public class FridgeServiceImpl implements FridgeService {

	@Autowired
	private FridgeDAO fridgeDAO;

	@Override
	public Fridge myFridge(String memberId) {
		return fridgeDAO.selectMyFridge(memberId);
	}
	
	

}
