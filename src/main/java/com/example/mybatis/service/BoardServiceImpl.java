package com.example.mybatis.service;

import org.springframework.stereotype.Service;

import com.example.mybatis.repository.BoardMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{

	
	private final BoardMapper mapper;
}
