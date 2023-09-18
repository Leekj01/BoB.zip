package com.bobzip.CRUD.recipe.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.servlet.ModelAndView;

import com.bobzip.CRUD.recipe.model.Paging;
import com.bobzip.CRUD.recipe.model.service.RecipeService;
import com.bobzip.CRUD.recipe.model.vo.Ingredient;
import com.bobzip.CRUD.recipe.model.vo.RecipeComment;
import com.bobzip.CRUD.recipe.model.vo.RecipeInfo;
import com.bobzip.CRUD.recipe.model.vo.RecipeSummary;


@Controller("recipeController")
@RequestMapping("/recipe")
public class RecipeController {
	
	String search;
	
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
		RecipeSummary recipeSummary = recipeService.selectSummary(recipeId);
		List<RecipeInfo> recipeInfo = recipeService.selectRecipeInfo(recipeId);
		List<Ingredient> ingredient = recipeService.selectIngredients(recipeId);
		List<RecipeComment> recipeComment = recipeService.getCommentsByRecipeId(Integer.parseInt(recipeId));
		mav.addObject("recipeComment", recipeComment);
		mav.addObject("recipeSummary",recipeSummary);
		mav.addObject("recipeInfo",recipeInfo);
		mav.addObject("ingredient",ingredient);
		mav.setViewName("recipe/recipeInfo");
		return mav;
	}
	
	@RequestMapping("/recipeInfoComments")
	public ModelAndView recipeInfoComment(ModelAndView mav,
			@RequestParam("recipeId") int recipeId,
			@RequestParam("memberId") String memberId,
			@RequestParam("memberNick") String memberNick,
			@RequestParam("replyComment") String replyComment) {
			System.out.println("레시피댓글");
			RecipeComment recipecomment = new RecipeComment();
			recipecomment.setRecipeId(recipeId);
			recipecomment.setMemberId(memberId);
			recipecomment.setMemberNick(memberNick);
			recipecomment.setReplyComment(replyComment);
	        
	        recipeService.insertComment(recipecomment);
	        
	        List<RecipeComment> recipecomments = recipeService.getCommentsByRecipeId(recipeId);
	        mav.addObject("Recipecomments", recipecomments);

	        mav.setViewName("redirect:/recipe/recipeInfo?recipeId=" + recipeId);
	        return mav;
	}
	
	@RequestMapping(value = "/deleteComment", method= RequestMethod.POST)
	public ResponseEntity<String> deleteComment(@RequestParam("commentNo") int commentNo) {
		System.out.println("댓글 삭제 실행");
		if (recipeService.deleteComment(commentNo)) {
			System.out.println("댓글삭제");
			return ResponseEntity.ok("댓글이 삭제되었습니다");
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("댓글 삭제 실패");
		}
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
		mav.setViewName("home");
		return mav;
	}
	
	@RequestMapping("/myrecipeForm")
	public ModelAndView myRecipe(ModelAndView mav, HttpSession session, Paging paging,
								@RequestParam(value="nowPage", required=false) String nowPage,
								@RequestParam(value="cntPerPage", required=false) String cntPerPage) {
		System.out.println("myrecipeform");
		String memberId = (String)session.getAttribute("memberLoggedIn");
		int total = recipeService.myrecipecountBoard(memberId);
		System.out.println(total);
    Map<String, Object> parameter = new HashMap();
		parameter.put("memberId", memberId);
		parameter.put("paging", paging);
		List<RecipeSummary> myrecipeResult = recipeService.myrecipeResult(parameter);
		System.out.println(myrecipeResult.size());
		mav.addObject("recipeSummary", myrecipeResult);
		mav.addObject("paging",paging);
		mav.setViewName("myrecipe/myRecipe");
		return mav;
	}

	@RequestMapping("/searchRecipe.do")
	public ModelAndView searchRecipe (ModelAndView mav,Paging paging,
			@RequestParam(value="inputedRecipeName", required=false) String inputedRecipeName,
			@RequestParam(value="nowPage", required=false) String nowPage,
			@RequestParam(value="cntPerPage", required=false) String cntPerPage) {
		if (inputedRecipeName != null) {
			inputedRecipeName = inputedRecipeName.trim();
			search = inputedRecipeName;
		}
		int total = recipeService.countSearchResult(search);
		if (nowPage == null && cntPerPage == null) {
			nowPage = "1";
			cntPerPage = "16";
		} else if (nowPage == null) {
			nowPage = "1";
		} else if (cntPerPage == null) {
			cntPerPage = "16";
		}
		paging = new Paging(total, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage));
    Map parameter = new HashMap();
		parameter.put("paging",paging);
		parameter.put("inputedRecipeName",search);
		List<RecipeSummary> searchResult = recipeService.selectSearchResult(parameter);
		mav.addObject("recipeSummary",searchResult);
		mav.addObject("paging",paging);
		mav.setViewName("recipe/recipeSearch");
		return mav;
	}
		
	@RequestMapping("/updatemyRecipeForm")
	public ModelAndView updatemyRecipe(ModelAndView mav, 
			@RequestParam("recipeId") String recipeId) {
		RecipeSummary update = recipeService.updateMyRecipe(recipeId);
		List<Ingredient> ingredient = recipeService.updateMyRecipeIngredient(recipeId);
		List<RecipeInfo> recipeInfo =  recipeService.updateMyRecipeInfo(recipeId);
		System.out.println("마이레시피 업데이트" + update);
		System.out.println("마이레시피 인그리언트" +ingredient);
		System.out.println("마이레시피 인포" + recipeInfo);
		mav.addObject("recipeSummary", update);
		mav.addObject("ingredient", ingredient);
		mav.addObject("recipeInfo", recipeInfo);
		mav.setViewName("myrecipe/myrecipeUpdate"); 
		return mav;
	}
	
	@RequestMapping(value = "/myrecipeImageDelete", method= RequestMethod.POST)
	public ResponseEntity<String> myrecipeImageDelete(@RequestParam("image") String image,@RequestParam("recipeId") String recipeId) {
		System.out.println("이미지 삭제 실행");
		RecipeSummary summary = new RecipeSummary(recipeId,image);
		if (recipeService.myrecipeImageDelete(summary)) {
			System.out.println("이미지 삭제");
			return ResponseEntity.ok("이미지가 삭제되었습니다");
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("이미지 삭제 실패");
		}
	}
	/*
	@RequestMapping("/myrecipeUpdate")
	public ModelAndView myrecipeUpdate(ModelAndView mav,
			@RequestParam("memberId") String memberId,
			@RequestParam("recipeName") String recipeName,
			@RequestParam("summary") String summary,
			@RequestParam("typeName") String typeName,
			@RequestParam("nationName") String nationName,
			@RequestParam("levelName") String levelName,
			@RequestParam("ingredientName") String ingredientName,
			@RequestParam("cookingStep") String cookingStep,
			@RequestParam("imageFile") MultipartFile imageFile,
			@RequestParam("recipeId") String recipeId,
			@RequestParam("rowNumber") int rowNumber) throws Exception{
		
		//파일 업로드
				String uploadFolder = "C:\\Users\\UserK\\BoB.zip\\src\\main\\webapp\\resources\\img\\recipe";
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
				
				RecipeSummary recipeSummary = new RecipeSummary(Integer.parseInt(recipeId),recipeName,summary,nationName,levelName,typeName,memberId,imageName);
				recipeService.updateMyRecipeSummary(recipeSummary);
				
				for (String name : ingredientsName) {
					Ingredient ingredient_ = new Ingredient(rowNumber,name, Integer.parseInt(recipeId));
					recipeService.updateMyRecipeIngredients(ingredient_);
				}
				
		return null;
	}
	*/
	@RequestMapping(value = "/deletemyRecipe", method= RequestMethod.POST)
	public ResponseEntity<String> deleteMyRecipe(@RequestParam("recipeId") String recipeId) {
		System.out.println("내 레시피 삭제");
		recipeService.deleteMyRecipeInfo(recipeId);
		recipeService.deleteMyRecipeIngredient(recipeId);
		boolean success = recipeService.deleteMyRecipeSummary(recipeId);
		if (success) {
			return ResponseEntity.ok("나의레시피가 삭제되었습니다");
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("나의레시피삭제 실패");
	
	@RequestMapping(value="/editComment", method=RequestMethod.POST)
	public ResponseEntity<String> editComment(@ModelAttribute RecipeComment recipeComment){
		boolean success = recipeService.editComment(recipeComment);
		System.out.println(success);
		if (success) {
			return ResponseEntity.ok("댓글이 수정되었습니다.");
		} else {
			return ResponseEntity.badRequest().body("댓글 수정에 실패했습니다.");
		}
	}
}
