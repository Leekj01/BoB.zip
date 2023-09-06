package com.bobzip.CRUD.recipe.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Paging {
	
	// 현재페이지, 시작페이지, 끝페이지, 게시글 총 갯수, 페이지당 글 갯수, 마지막페이지, SQL쿼리에 쓸 start, end
	private int nowPage, startPage, endPage, total, cntPerPage, lastPage, start, end;
	//화면에 표시할 페이지 갯수
	private int cntPage = 16;
	
	public Paging() {}
	
	public Paging (int total, int nowPage, int cntPerPage) {
		setNowPage(nowPage);
		setCntPerPage(cntPerPage);
		setTotal(total);
		calcLastPage(getTotal(), getCntPerPage());
		calcStartEndPage(getNowPage(), cntPage);
		calcStartEnd(getNowPage(), getCntPerPage());
	}
	
	//총 페이지의 마지막 구하기
	public void calcLastPage(int total, int cntPerPage) {
		setLastPage((int)Math.ceil((double)total/(double)cntPerPage));
	}
	
	//표시될 페이지 시작, 끝 구하기 (모든 페이지를 화면에 표시할 수 없다.)
	public void calcStartEndPage(int nowPage, int cntPage) {
		setEndPage(((int)Math.ceil((double)nowPage / (double)cntPage)) * cntPage);
		if (getLastPage() < getEndPage()) {
			setEndPage(getLastPage());
		}
		setStartPage(getEndPage() - cntPage + 1);
		if (getStartPage() < 1 ) {
			setStartPage(1);
		}
	}
	
	//쿼리에 달라고 요청하기위한 start, end 값 구하기
	public void calcStartEnd(int nowPage, int cntPerPage) {
		setEnd(nowPage * cntPage);
		setStart(getEnd() - cntPerPage + 1);
	}
	
}
