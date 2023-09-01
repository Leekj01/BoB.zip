package com.bobzip.CRUD.fridge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bobzip.CRUD.fridge.model.service.FridgeService;

@RestController
@RequestMapping("/api")
public class IngredientDB {
	
	@Autowired 
	private FridgeService fridgeService;
	
	@RequestMapping("/addIngredients")
	public void addIngredients () {
		StringBuilder urlBuilder = new StringBuilder
				("	http://211.237.50.150:7080/openapi/8079d06a600c89830c2027c3f3e2cb104751e76b47c94f0f247eef734d75458a/json/Grid_20150827000000000227_1/1/5");
	}
}
