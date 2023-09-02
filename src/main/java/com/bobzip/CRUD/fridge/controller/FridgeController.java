package com.bobzip.CRUD.fridge.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bobzip.CRUD.fridge.model.service.FridgeService;
import com.bobzip.CRUD.fridge.model.vo.Fridge;

@Controller("fridgeController")
@RequestMapping("/fridge")
public class FridgeController {
	
	@Autowired
	private FridgeService fridgeService;
	
	@RequestMapping(value = "/myFridgeForm", method = RequestMethod.GET)
	public ModelAndView myFridge(@RequestParam("memberId") String memberId,ModelAndView mav) {
		List<Fridge> myFridge = fridgeService.myFridge(memberId);
		List<String> allIngredients = fridgeService.allIngredients();
		mav.addObject("myFridge",myFridge);
		mav.addObject("allIngredients", allIngredients);
		mav.setViewName("/fridge/myfridge");
		return mav;
	}
	
}
