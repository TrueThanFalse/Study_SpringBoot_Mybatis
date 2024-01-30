package com.example.mybatis.security;

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
public class AuthVO {
/*
 * CREATE TABLE auth_member(
	email VARCHAR(200) NOT NULL,
	auth VARCHAR(100) NOT NULL,
	FOREIGN KEY(email) REFERENCES member(email)
	);
 */
	private String email;
	private String auth;
}
