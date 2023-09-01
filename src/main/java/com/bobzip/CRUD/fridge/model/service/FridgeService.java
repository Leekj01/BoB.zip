package com.bobzip.CRUD.fridge.model.service;

import java.util.List;

import com.bobzip.CRUD.fridge.model.vo.Fridge;

public interface FridgeService {

	List<Fridge> myFridge(String memberId);

}
