package com.example.mybatis.service;

import java.util.List;

import com.example.mybatis.domain.CommentVO;
import com.example.mybatis.domain.PagingVO;
import com.example.mybatis.handler.PagingHandler;

public interface CommentService {

	int register(CommentVO cvo);

	PagingHandler getList(long bno, PagingVO pgvo);

	int edit(CommentVO cvo);

}
