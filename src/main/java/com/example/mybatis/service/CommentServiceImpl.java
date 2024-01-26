package com.example.mybatis.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.mybatis.domain.CommentVO;
import com.example.mybatis.domain.PagingVO;
import com.example.mybatis.handler.PagingHandler;
import com.example.mybatis.repository.CommentMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{

	private final CommentMapper cmapper;

	@Override
	public int register(CommentVO cvo) {
		// TODO Auto-generated method stub
		return cmapper.insertCvo(cvo);
	}

//	@Override
//	public List<CommentVO> getList(long bno) {
//		// TODO Auto-generated method stub
//		return cmapper.getList(bno);
//	}

	@Override
	public int edit(CommentVO cvo) {
		// TODO Auto-generated method stub
		return cmapper.updateCvo(cvo);
	}

	@Override
	@Transactional
	public PagingHandler getList(long bno, PagingVO pgvo) {
		// TODO Auto-generated method stub
		/*
		 * Controller에서 PagingHandler 생성 작업을 처리해도 되지만
		 * Service에서 처리하면 속도가 더 빨라지고
		 * 트랜잭션 처리도 효율적으로 할 수 있다.
		 */
		
		// totalCount
		int totalCount = cmapper.bnoTotalCount(bno);
				
		// List
		List<CommentVO> list = cmapper.getList(bno, pgvo);
				
		PagingHandler ph = new PagingHandler(pgvo, totalCount, list);
				
		return ph;
	}
}
