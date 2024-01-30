package com.example.mybatis.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.mybatis.repository.MemberMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomUserSerivice implements UserDetailsService {

	@Autowired
	private MemberMapper mMapper;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		// username : 폼 로그인의 파라미터유저네임에서 가져옴
		MemberVO mvo = mMapper.selectEmail(username);
		mvo.setAuthList(mMapper.selectAuths(username));
		
		return new AuthMember(mvo);
	}

}
