package com.example.mybatis.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.mybatis.security.MemberVO;
import com.example.mybatis.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/member/*")
public class MemberController {

	private final MemberService msv;
	
	private final PasswordEncoder passwordEncoder;
	
	@GetMapping("/member/register")
	public String join() {
		return "/member/join";
	}
	
	@PostMapping("/register")
	public String register(MemberVO mvo) {
		log.info("@@@@@ mvo >>> " + mvo);
		mvo.setPwd(passwordEncoder.encode(mvo.getPwd()));
		int isOK = msv.insert(mvo);
		return isOK > 0 ? "/index" : "/member/register";
	}
	
	@GetMapping("/member/login")
	public void login() {}

}
