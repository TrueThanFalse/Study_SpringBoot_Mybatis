package com.example.mybatis.controller;

import java.util.List;

import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.mybatis.domain.CommentVO;
import com.example.mybatis.domain.PagingVO;
import com.example.mybatis.handler.PagingHandler;
import com.example.mybatis.service.CommentService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/comment/*")
@RequiredArgsConstructor
public class CommentController {

	private final CommentService csv;
	
	@PutMapping("/register")
	@ResponseBody
	public String register(@RequestBody CommentVO cvo) { // String : body의 타입
		log.info("@@@@@ Get register Method Join Success");
		int isOK = csv.register(cvo);
		return isOK > 0 ? "1" : "0";
	}
	/*
	@GetMapping("/getList/{bno}")
	@ResponseBody
	public List<CommentVO> getList(@PathVariable("bno") long bno,
			@PathVariable("page") int page) {
		log.info("@@@@@ Get register Method Join Success");
		log.info("@@@@@ bno @@@ "+ bno);

		List<CommentVO> list = csv.getList(bno);
		return list;
	}
	*/
	@GetMapping("/getList/{bno}/{page}")
	@ResponseBody
	public PagingHandler getList(@PathVariable("bno") long bno,
			@PathVariable("page") int page) {
		log.info("@@@@@ Get register Method Join Success");
		log.info("@@@@@ bno @@@ "+ bno + "page " + page);
		
		// List<CommentVO>와 PagingHandler를 전송해야 됨
		// => 하지만 비동기는 하나의 객체만 전송 가능
		// => PagingHandler에 List<CommentVO>를 담아서 전송해야 한다.
		
		PagingVO pgvo = new PagingVO(page, 5); // 한 페이지에 5개씩 표시
		
		PagingHandler ph = csv.getList(bno, pgvo);
		
		return ph;
	}
	
	@PutMapping("/edit")
	@ResponseBody
	public String edit(@RequestBody CommentVO cvo) {
		log.info("@@@@@ cvo @@@ ", cvo);
		int isOK = csv.edit(cvo);
		return isOK > 0 ? "1" : "0";
	}
}
