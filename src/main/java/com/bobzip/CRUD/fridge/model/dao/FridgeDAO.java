package com.bobzip.CRUD.fridge.model.dao;

import java.util.List;

import com.bobzip.CRUD.fridge.model.vo.Fridge;

public interface FridgeDAO {

	List<Fridge> selectMyFridge(String memberId);

}
