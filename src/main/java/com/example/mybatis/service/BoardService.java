package com.example.mybatis.service;

import java.util.List;

import com.example.mybatis.domain.BoardVO;

public interface BoardService {

	int registerBvo(BoardVO bvo);

	List<BoardVO> getBvoList();

	BoardVO getBvo(long bno);

	int deleteBvo(long bno);

	int editBvo(BoardVO bvo);

}
