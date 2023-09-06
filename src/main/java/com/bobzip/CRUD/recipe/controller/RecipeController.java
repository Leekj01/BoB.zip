package com.bobzip.CRUD.recipe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bobzip.CRUD.recipe.model.Paging;
import com.bobzip.CRUD.recipe.model.service.RecipeService;
import com.bobzip.CRUD.recipe.model.vo.RecipeSummary;

@Controller("recipeController")
@RequestMapping("/recipe")
public class RecipeController {
	
	@Autowired
	private RecipeService recipeService;
	
	@RequestMapping("/recipeForm")
	public ModelAndView recipeForm(Paging paging,ModelAndView mav,
			@RequestParam(value="nowPage", required=false) String nowPage,
			@RequestParam(value="cntPerPage", required=false) String cntPerPage) {
		int total = recipeService.countBoard();
		if (nowPage == null && cntPerPage == null) {
			nowPage = "1";
			cntPerPage = "16";
		} else if (nowPage == null) {
			nowPage = "1";
		} else if (cntPerPage == null) {
			cntPerPage = "16";
		}
		paging = new Paging(total, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage));
		System.out.println(paging.getStart());
		System.out.println(paging.getEnd());
		mav.addObject("paging", paging);
		mav.addObject("recipeSummary", recipeService.selectBoard(paging));
		mav.setViewName("recipe/recipeHome");
		return mav;
	}
}
