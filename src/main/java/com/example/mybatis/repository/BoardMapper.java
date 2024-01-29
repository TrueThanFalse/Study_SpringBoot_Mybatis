package com.example.mybatis.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.annotation.Bean;

import com.example.mybatis.domain.BoardDTO;
import com.example.mybatis.domain.BoardVO;
import com.example.mybatis.domain.PagingVO;

/*
 * DataBaseConfig 내부의 메서드
 * @Bean
	public SqlSessionTemplate sqlsessionTemplate(SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
		// DAO에 맵퍼 어노테이션을 사용할 수 있도록 함
	}
	위 코드로 @Mapper를 인식하도록 만든 것이다.
 */
@Mapper
public interface BoardMapper {

	List<BoardVO> selectAllBvoList(PagingVO pgvo);

	BoardVO selectOneBvo(long bno);

	int deleteBvo(long bno);

	int updateBvo(BoardVO bvo);

	int selectAllBvoCount(PagingVO pgvo);

	int insertBvo(BoardVO bvo);

	long getBno();

}
