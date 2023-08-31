package com.bobzip.CRUD.member.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bobzip.CRUD.member.model.service.MemberService;
import com.bobzip.CRUD.member.model.vo.Member;

@Controller("memberController")
@RequestMapping("/member")
public class MemberController {

	@Autowired
	private MemberService memberservice;
	
	//로그인창 요청
	@RequestMapping(value = "/loginForm", method = RequestMethod.GET)
	public String loginForm() {
	    return "member/loginForm";
	}
	//로그인
	@RequestMapping(value="/login.do" ,method = RequestMethod.POST)
	public ModelAndView login(@RequestParam Map<String, String> loginMap,
						HttpSession session, ModelAndView mav) throws Exception {
		
		Member member = memberservice.login(loginMap);
		if (member != null) {
			String memberId = member.getMemberId();
			String memberNick = member.getMemberNick();
			session.setAttribute("memberLoggedIn", memberId);
			session.setAttribute("memberNick", memberNick);
			mav.setViewName("redirect:/");
		} else {
			mav.addObject("message","아이디나 비밀번호가 틀립니다. 다시로그인해주세요");
			mav.setViewName("member/loginForm");
		}
		
		return mav;
	}
	//회원가입창 요청
	@RequestMapping(value="/addMemberForm", method=RequestMethod.GET)
	public String addMemberForm() {
		return "member/addMemberForm";
	}
	//회원가입
	@RequestMapping(value="/addMember.do", method=RequestMethod.POST)
	public ResponseEntity addMember(@ModelAttribute Member member,
					HttpServletRequest request, HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		String message = null;
		ResponseEntity resEntity = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type","text/html; charset=utf-8");
		try {
			memberservice.addMember(member);
			message  = "<script>";
			message += "alert('회원 가입을 마쳤습니다. 로그인창으로 이동합니다.');";
			message += "location.href='"+request.getContextPath()+"/member/loginForm';";
			message += "</script>";
		} catch(Exception e) {
			message  = "<script>";
			message += "alert('작업 중 오류가 발생했습니다. 다시 시도해주세요');";
			message += "location.href='"+request.getContextPath()+"/member/memberForm';";
			message += "</script>";
			e.printStackTrace();
		}
		resEntity = new ResponseEntity(message, responseHeaders, HttpStatus.OK);
		return resEntity;
	}
	//아이디 중복확인
	@RequestMapping(value="/overlapped.do" ,method = RequestMethod.POST)
	public ResponseEntity overlapped(@RequestParam("memberId") String memberId,
						HttpServletRequest request, HttpServletResponse response) throws Exception{
		ResponseEntity resEntity = null;
		String result = memberservice.overlapped(memberId);
		resEntity =new ResponseEntity(result, HttpStatus.OK);
		return resEntity;
	}
	//로그아웃 
	@RequestMapping(value = "/logout.do", method = RequestMethod.GET)
	public String logout (HttpSession session) {
		session.removeAttribute("memberLoggedIn");
		return "redirect:/";
	}
	//회원정보 수정 페이지 요청
	@RequestMapping(value="/updateForm", method=RequestMethod.GET)
	public ModelAndView deleteForm(@RequestParam("memberId") String memberId, ModelAndView mav) {
		Member memberInfo = memberservice.getMemberInfo(memberId);
		mav.addObject("memberInfo", memberInfo);
		mav.setViewName("member/updateForm");
		return mav;
	}
	
	//회원정보 수정 
	@RequestMapping(value = "/updateMember.do", method = RequestMethod.POST)
	public String updateMember(@ModelAttribute Member member,
									HttpServletRequest request, HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		memberservice.updateMember(member);
		return "redirect:/";
	} 
	
}
