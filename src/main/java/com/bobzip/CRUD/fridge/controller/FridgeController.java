package com.bobzip.CRUD.fridge.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bobzip.CRUD.fridge.model.service.FridgeService;
import com.bobzip.CRUD.fridge.model.vo.Fridge;
import com.bobzip.CRUD.recipe.model.service.RecipeService;

@Controller("fridgeController")
@RequestMapping("/fridge")
public class FridgeController {
	
	@Autowired
	private FridgeService fridgeService;
	@Autowired
	private RecipeService recipeService;
	
	@RequestMapping(value = "/myFridgeForm", method = RequestMethod.GET)
	public ModelAndView myFridge(ModelAndView mav, HttpSession session) {
		String memberId = (String) session.getAttribute("memberLoggedIn");
		List<Fridge> myFridge = fridgeService.myFridge(memberId);
		List<String> allIngredients = fridgeService.allIngredients();
		mav.addObject("myFridge",myFridge);
		mav.addObject("allIngredients", allIngredients);
		mav.setViewName("/fridge/myfridge");
		return mav;
	}
	
	@RequestMapping(value="/addFridge.do", method = RequestMethod.POST)
	public String addFridge(@RequestParam("selectedIngredients") List<String> ingredients, HttpSession session) {
		String memberId = (String)session.getAttribute("memberLoggedIn");
		List<Fridge> fridges = new ArrayList<Fridge>();
		for (int i = 0; i<ingredients.size(); i++) {
			Fridge fridge = new Fridge();
			String ingredient = ingredients.get(i);
			fridge.setIngredientName(ingredient);
			fridge.setMemberId(memberId);
			fridges.add(fridge);
		}
		fridgeService.addFridge(fridges);
	    
		return "redirect:/fridge/myFridgeForm";
	}
	
	@RequestMapping(value="/deleteFridge.do", method=RequestMethod.GET)
	public String deleteFridge(@RequestParam("rowNumber")int rowNumber, HttpSession session) {
		System.out.println(rowNumber);
		String memberId = (String)session.getAttribute("memberLoggedIn");
		fridgeService.deleteFridge(rowNumber);
		
		return "redirect:/fridge/myFridgeForm";
	}
	
	@RequestMapping(value="/searchRecipe.do", method=RequestMethod.GET)
	public ModelAndView searchRecipe(ModelAndView mav,
								@RequestParam("myFridge")Fridge myFridge) {
		
		
		
		
		
		return null;
	}
}
