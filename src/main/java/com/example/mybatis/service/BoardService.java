package com.example.mybatis.service;

import java.util.List;

import com.example.mybatis.domain.BoardVO;
import com.example.mybatis.domain.PagingVO;

public interface BoardService {

	int registerBvo(BoardVO bvo);

	List<BoardVO> getBvoList(PagingVO pgvo);

	BoardVO getBvo(long bno);

	int deleteBvo(long bno);

	int editBvo(BoardVO bvo);

	int getTotalCount(PagingVO pgvo);

}
