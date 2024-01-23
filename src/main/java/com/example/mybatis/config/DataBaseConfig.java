package com.example.mybatis.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@PropertySource("classpath:/application.properties")
//해당 프로퍼티스의 값을 사용할 수 있도록 해주는 어노테이션
public class DataBaseConfig {
	// 마이바티스를 사용하기 위한 Config 설정
	// 만약 JPA를 사용할 것이라면 DataBaseConfig는 만들 필요가 없다.

	// import org.springframework.context.ApplicationContext;
	@Autowired
	private ApplicationContext applicationContext;
	
	// application.properties의 mybatis.mapper-locations의 값인
	// classpath:/mappers/**/*Mapper.xml를 저장시키는 어노테이션
	@Value("${mybatis.mapper-locations}")
	private String mapperLocations;
	
	@Bean
	@ConfigurationProperties(prefix = "spring.datasource")
	public HikariConfig hikariConfig() {
		return new HikariConfig();
	}

	// import 없이 해당 값을 바로 사용하는 방법
	@Bean
	public org.apache.ibatis.session.Configuration mybatisConfig() {
		return new org.apache.ibatis.session.Configuration();
		// 세션 팩토리의 구현체 자체를 활용해서 주입 받는 방법
	}
	
	@Bean
	public DataSource dataSource() throws Exception {
		DataSource dataSource = new HikariDataSource(hikariConfig());
		return dataSource;
	}
	
	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean =
				new SqlSessionFactoryBean(); // sqlSession 객체 생성
		
		sqlSessionFactoryBean.setDataSource(dataSource); // dataSource의 정보 추가
		
		sqlSessionFactoryBean.setConfigLocation(
				applicationContext.getResource("classpath:/mappers/mybatis-config.xml"));
		
		sqlSessionFactoryBean.setMapperLocations(
				// Path를 활용해서 가져오는 방법
				new PathMatchingResourcePatternResolver().getResources(mapperLocations));
		
		return sqlSessionFactoryBean.getObject();
	}
	
	@Bean
	public SqlSessionTemplate sqlsessionTemplate(SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
		// DAO에 맵퍼 어노테이션을 사용할 수 있도록 함
	}
}
