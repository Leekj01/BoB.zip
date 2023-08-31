package com.bobzip.CRUD.fridge.model.dao;

import com.bobzip.CRUD.fridge.model.vo.Fridge;

public interface FridgeDAO {

	Fridge selectMyFridge(String memberId);

}
