package com.bobzip.CRUD.member.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
	
	
	@RequestMapping(value = "/loginForm", method = RequestMethod.GET)
	public String loginForm() {
	    return "member/loginForm";
	}
	
	@RequestMapping(value="/login.do" ,method = RequestMethod.POST)
	public ModelAndView login(@RequestParam Map<String, String> loginMap,
						HttpSession session, ModelAndView mav) {
		
		Member member = memberservice.login(loginMap);
		if (member != null) {
			String memberId = member.getMemberId();
			session.setAttribute("memberLoggedIn", memberId);
			mav.setViewName("redirect:/");
		} else {
			mav.addObject("message","아이디나 비밀번호가 틀립니다. 다시로그인해주세요");
			mav.setViewName("member/loginForm");
		}
		
		return mav;
	}
	

}
