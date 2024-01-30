package com.example.mybatis.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.mybatis.security.AuthVO;
import com.example.mybatis.security.MemberVO;

@Mapper
public interface MemberMapper {

	MemberVO selectEmail(String username);

	List<AuthVO> selectAuths(String username);

	void insert(MemberVO mvo);

	int insertAuthInit(MemberVO mvo);

}
