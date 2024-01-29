package com.example.mybatis.service;

import java.util.List;

import com.example.mybatis.domain.BoardDTO;
import com.example.mybatis.domain.BoardVO;
import com.example.mybatis.domain.PagingVO;

public interface BoardService {

	int registerDTO(BoardDTO bdto);

	List<BoardVO> getBvoList(PagingVO pgvo);

	int deleteBvo(long bno);

	int editBvo(BoardVO bvo);

	int getTotalCount(PagingVO pgvo);

	int registerBvo(BoardVO bvo);

	BoardDTO getDetail(long bno);

}
