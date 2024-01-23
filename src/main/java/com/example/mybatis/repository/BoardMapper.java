package com.example.mybatis.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.annotation.Bean;

@Mapper
/*
 * DataBaseConfig의 메서드
 * @Bean
	public SqlSessionTemplate sqlsessionTemplate(SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
		// DAO에 맵퍼 어노테이션을 사용할 수 있도록 함
	}
	로 맵퍼를 인식함
 */
public interface BoardMapper {

}
