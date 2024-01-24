package com.example.mybatis.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.mybatis.domain.BoardVO;
import com.example.mybatis.service.BoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
@RequestMapping("/board/*")
@Slf4j
@RequiredArgsConstructor
public class BoardController {

	private final BoardService msv;
	
	@GetMapping("/register")
	public void register() {
		log.info("@@@@@ Get register Method Join Success");
	}
	
	@PostMapping("/register")
	public String register(BoardVO bvo, RedirectAttributes re) {
		log.info("@@@@@ Post register Method Join Success");
		
		int isOK = msv.registerBvo(bvo);
		log.info("@@@@@ Post Register @@@ " + (isOK > 0 ? "Success" : "Fail"));
		
		re.addFlashAttribute("registerBvoMsg", isOK);
		return "redirect:/";
		// index.html로 전송할 때는 항상 redirect:/를 사용해야 URL에 맵핑 경로가 남지 않는다.
	}
	
	@GetMapping("/list")
	public void list(Model m) {
		log.info("@@@@@ Get list Method Join Success");
		
		List<BoardVO> list = msv.getBvoList();
		if(list.size() > 0) {
			log.info("@@@@@ List<BoardVO> list @@@ Success");
		}
		
		m.addAttribute("list", list);
	}
	
	@GetMapping("/detail")
	public void detail(@RequestParam("bno") long bno, Model m) {
		log.info("@@@@@ Get detail Method Join Success");
		
		BoardVO bvo = msv.getBvo(bno);
		m.addAttribute("bvo", bvo);
	}
	
	@PostMapping("/modify")
	public void modify(BoardVO bvo, Model m) {
		log.info("@@@@@ Post modify Method Join Success");
		
		m.addAttribute("bvo", bvo);
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("bno") long bno) {
		log.info("@@@@@ Get delete Method Join Success");
		
		int isOK = msv.deleteBvo(bno);
		log.info("@@@@@ deleteBvo @@@ " + (isOK > 0 ? "Success" : "Fail"));
		
		return "redirect:/";
	}
	
	@PostMapping("/edit")
	public String edit(BoardVO bvo) {
		log.info("@@@@@ Post edit Method Join Success");
		
		int isOK = msv.editBvo(bvo);
		log.info("@@@@@ editBvo @@@ " + (isOK > 0 ? "Success" : "Fail"));
		
		return "redirect:/board/list";
	}

}
