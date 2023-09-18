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
import com.bobzip.CRUD.recipe.model.Paging;
import com.bobzip.CRUD.recipe.model.service.RecipeService;
import com.bobzip.CRUD.recipe.model.vo.RecipeSummary;

@Controller("fridgeController")
@RequestMapping("/fridge")
public class FridgeController {
	
	
	@Autowired
	private FridgeService fridgeService;
	
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
	public ModelAndView searchRecipe(ModelAndView mav, Paging paging,
								HttpSession session,
								@RequestParam(value="nowPage", required=false) String nowPage,
								@RequestParam(value="cntPerPage", required=false) String cntPerPage) {
		String memberId = (String)session.getAttribute("memberLoggedIn");
		List<Fridge> myFridge = fridgeService.myFridge(memberId);
		List<String> ingredients = new ArrayList<String>();
		for (int i = 0; i < myFridge.size(); i++) {
			String ingredient = myFridge.get(i).getIngredientName();
			ingredients.add(ingredient);
		}
		if (nowPage == null && cntPerPage == null) {
			nowPage = "1";
			cntPerPage = "16";
		} else if (nowPage == null) {
			nowPage = "1";
		} else if (cntPerPage == null) {
			cntPerPage = "16";
		}
		List<RecipeSummary> searchingResult = fridgeService.searchRecipe(ingredients);
		System.out.println(searchingResult.size());
		mav.addObject("recipeSummary",searchingResult);
		paging = new Paging(searchingResult.size(), Integer.parseInt(nowPage), Integer.parseInt(cntPerPage));
		mav.addObject("paging",paging);
		mav.setViewName("recipe/recipeHome");
		return mav;
		
	}
}
