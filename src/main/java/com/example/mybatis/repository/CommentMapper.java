package com.example.mybatis.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.mybatis.domain.CommentVO;
import com.example.mybatis.domain.PagingVO;

@Mapper
public interface CommentMapper {

	int insertCvo(CommentVO cvo);

	List<CommentVO> getList(@Param("bno") long bno, @Param("pgvo") PagingVO pgvo);

	int updateCvo(CommentVO cvo);

	int bnoTotalCount(long bno);

	int delete(CommentVO cvo);

}
