package com.example.mybatis.service;

import org.springframework.stereotype.Service;

import com.example.mybatis.repository.CommentMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{

	private final CommentMapper cmapper;
}
