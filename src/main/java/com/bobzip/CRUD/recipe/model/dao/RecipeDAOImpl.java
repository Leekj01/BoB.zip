package com.bobzip.CRUD.recipe.model.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bobzip.CRUD.recipe.model.Paging;
import com.bobzip.CRUD.recipe.model.vo.RecipeSummary;

@Repository("recipeDAO")
public class RecipeDAOImpl implements RecipeDAO{
	
	@Autowired
	SqlSession sqlSession;

	@Override
	public List<RecipeSummary> selectBoard(Paging paging) {
		return sqlSession.selectList("mapper.recipe.selectRecipeSummaryBoard",paging);
	}

	@Override
	public int countBoard() {
		return sqlSession.selectOne("mapper.recipe.countRecipeSummary");
	}

}
