package com.bobzip.CRUD.member.model.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Member {
	
	private String memberId;
	private String memberPwd;
	private String memberNick;
	private String phone1;
	private String phone2;
	private String phone3;
	private String email1;
	private String email2;
}
