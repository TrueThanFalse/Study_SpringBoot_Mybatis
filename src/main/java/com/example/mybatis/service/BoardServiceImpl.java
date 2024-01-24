package com.example.mybatis.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.mybatis.domain.BoardVO;
import com.example.mybatis.repository.BoardMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{

	private final BoardMapper bmapper;

	@Override
	public int registerBvo(BoardVO bvo) {
		// TODO Auto-generated method stub
		return bmapper.insertBvo(bvo);
	}

	@Override
	public List<BoardVO> getBvoList() {
		// TODO Auto-generated method stub
		return bmapper.selectAllBvoList();
	}

	@Override
	public BoardVO getBvo(long bno) {
		// TODO Auto-generated method stub
		return bmapper.selectOneBvo(bno);
	}

	@Override
	public int deleteBvo(long bno) {
		// TODO Auto-generated method stub
		return bmapper.deleteBvo(bno);
	}

	@Override
	public int editBvo(BoardVO bvo) {
		// TODO Auto-generated method stub
		return bmapper.updateBvo(bvo);
	}
}
