package com.bobzip.CRUD.member.model.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


public class Member {
	
	private String memberId;
	private String memberPwd;
	private String memberNick;
	private String phone;
	private String email;
	private Date regDate;
	
	public Member () {}
	public Member (String memberId,String memberPwd, String memberNick, String phone, String email, Date regDate) {
		this.memberId = memberId;
		this.memberPwd = memberPwd;
		this.memberNick = memberNick;
		this.phone = phone;
		this.email = email;
		this.regDate = regDate;
	}
	
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getMemberPwd() {
		return memberPwd;
	}
	public void setMemberPwd(String memberPwd) {
		this.memberPwd = memberPwd;
	}
	public String getMemberNick() {
		return memberNick;
	}
	public void setMemberNick(String memberNick) {
		this.memberNick = memberNick;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	
}
