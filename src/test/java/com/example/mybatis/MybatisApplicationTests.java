package com.example.mybatis;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.mybatis.domain.BoardVO;
import com.example.mybatis.repository.BoardMapper;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MybatisApplication.class)
class MybatisApplicationTests {

	@Autowired
	private BoardMapper bmapper;
	
	@Test
	void contextLoads() {
		
		for(int i=0; i<177; i++) {
			BoardVO bvo = BoardVO.builder()
					.title("Test title " + i)
					.writer("tester@test.com")
					.content("Test Content " + i)
					.build();
			
			bmapper.insertBvo(bvo);
		}
	}

}
