package com.example.mybatis.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.mybatis.security.MemberVO;
import com.example.mybatis.service.MemberService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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

	private void logout(HttpServletRequest req, HttpServletResponse res) {
		// 로그아웃이 필요한 상황이면 이 메서드를 호출하여 로그아웃을 적용시킬 수 있다.
		
		Authentication authentication =
				SecurityContextHolder.getContext().getAuthentication();
		
		new SecurityContextLogoutHandler().logout(req, res, authentication);
		// 실제 로그아웃 적용 완료
	}
}
