package com.bobzip.CRUD.recipe.controller;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.servlet.ModelAndView;

import com.bobzip.CRUD.recipe.model.Paging;
import com.bobzip.CRUD.recipe.model.service.RecipeService;
import com.bobzip.CRUD.recipe.model.vo.Ingredient;
import com.bobzip.CRUD.recipe.model.vo.RecipeInfo;
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
		mav.addObject("paging", paging);
		mav.addObject("recipeSummary", recipeService.selectBoard(paging));
		mav.setViewName("recipe/recipeHome");
		return mav;
	}
	
	@RequestMapping("/recipeInfo")
	public ModelAndView recipeInfo(ModelAndView mav,
			@RequestParam("recipeId")String recipeId) {
		System.out.println(recipeId);
		List<RecipeInfo> recipeInfo = recipeService.selectRecipeInfo(recipeId);
		List<Ingredient> ingredient = recipeService.selectIngredients(recipeId);
		mav.addObject("recipeInfo",recipeInfo);
		mav.addObject("ingredient",ingredient);
		mav.setViewName("recipe/recipeInfo");
		return mav;
	}
	
	@RequestMapping("/recipeUploadForm")
	public ModelAndView recipeUploadForm(HttpSession session, ModelAndView mav) {
		String memberId = (String)session.getAttribute("memberLoggedIn");
		mav.addObject("memberLoggedIn", memberId);
		mav.setViewName("recipe/recipeUploadForm");
		return mav;
	}
	
	@RequestMapping("/recipeUpload")
	public ModelAndView recipeUpload(ModelAndView mav,
			@RequestParam("memberId") String memberId,
			@RequestParam("recipeName") String recipeName,
			@RequestParam("summary") String summary,
			@RequestParam("typeName") String typeName,
			@RequestParam("nationName") String nationName,
			@RequestParam("levelName") String levelName,
			@RequestParam("ingredientName") String ingredientName,
			@RequestParam("cookingStep") String cookingStep,
			@RequestParam("imageFile") MultipartFile imageFile) throws Exception {
		
		//파일 업로드
		String uploadFolder = "D:\\LYH\\BoB.zip\\BoB.zip\\src\\main\\webapp\\resources\\img\\recipe";
		String imageName = imageFile.getOriginalFilename();
		File saveFile = new File(uploadFolder, imageName);
		try {
			imageFile.transferTo(saveFile);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		//데이터베이스에 데이터 넣기
		String [] ingredientsName = ingredientName.split(",");
		String [] cookingSteps = cookingStep.split(",");
		
		RecipeSummary recipeSummary = new RecipeSummary(recipeName,summary,nationName,levelName,typeName,memberId,imageName);
		recipeService.insertRecipeSummary(recipeSummary);
		int recipeId = recipeService.getRecipeId();
		
		for (String name : ingredientsName) {
			Ingredient ingredient_ = new Ingredient(name, recipeId);
			recipeService.insertIngredient(ingredient_);
		}

		for (int i = 0; i < cookingSteps.length; i++) {
			RecipeInfo recipeInfo = new RecipeInfo(recipeId,i+1,cookingSteps[i]);
			recipeService.insertRecipeInfo(recipeInfo);
		}
		return null;
	}
}
