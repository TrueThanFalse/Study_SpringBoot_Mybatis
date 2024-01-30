package com.example.mybatis.service;

import org.springframework.stereotype.Service;

import com.example.mybatis.repository.MemberMapper;
import com.example.mybatis.security.MemberVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

	private final MemberMapper mMapper;

	@Override
	public int insert(MemberVO mvo) {
		// TODO Auto-generated method stub
		mMapper.insert(mvo);
		// 외래키 설정으로 인하여 반드시 member 부터 insert 되어야 함
		return mMapper.insertAuthInit(mvo);
	}
}
