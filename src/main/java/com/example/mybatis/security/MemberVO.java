package com.example.mybatis.security;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MemberVO {
/*
 * CREATE TABLE member(
	email VARCHAR(200) not null,
	pwd VARCHAR(256) NOT NULL,
	nick_name VARCHAR(100),
	reg_at DATETIME DEFAULT NOW(),
	last_login DATETIME,
	PRIMARY KEY(email)
	);
 */
	private String email;
	private String pwd;
	private String nickName;
	private String regAt;
	private String lastLogin;
	
	private List<AuthVO> authList;
}
