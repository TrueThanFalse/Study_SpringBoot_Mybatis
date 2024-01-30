package com.example.mybatis.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.mybatis.domain.BoardDTO;
import com.example.mybatis.domain.BoardVO;
import com.example.mybatis.domain.FileVO;
import com.example.mybatis.domain.PagingVO;
import com.example.mybatis.handler.FileHandler;
import com.example.mybatis.handler.PagingHandler;
import com.example.mybatis.service.BoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
@RequestMapping("/board/*")
@Slf4j
@RequiredArgsConstructor
public class BoardController {

	private final BoardService bsv;
	private final FileHandler fh;
	
	@GetMapping("/register")
	public void register() {
		log.info("@@@@@ Get register Method Join Success");
	}
	
	/*
	@PostMapping("/register")
	public String register(BoardVO bvo, RedirectAttributes re) {
		log.info("@@@@@ Post register Method Join Success");
		
		int isOK = msv.registerBvo(bvo);
		log.info("@@@@@ Post Register @@@ " + (isOK > 0 ? "Success" : "Fail"));
		
		re.addFlashAttribute("registerBvoMsg", isOK);
		return "redirect:/";
		// index.html로 전송할 때는 항상 redirect:/를 사용해야 URL에 맵핑 경로가 남지 않는다.
	}
	*/
	
	@PostMapping("/register")
	public String register(BoardVO bvo, RedirectAttributes re,
			@RequestParam(name="files", required = false) MultipartFile[] files) {
		log.info("@@@@@ Post register Method Join Success");
		
		List<FileVO> flist = null;
		if(files[0].getSize() > 0 || files != null) {
			// 파일 핸들러 작업
			flist = fh.uploadFiles(files);
		}
		
		int isOK = bsv.registerDTO(new BoardDTO(bvo, flist));
		log.info("@@@@@ Post Register @@@ " + (isOK > 0 ? "Success" : "Fail"));
		
		re.addFlashAttribute("registerBvoMsg", isOK);
		return "redirect:/";
		// index.html로 전송할 때는 항상 redirect:/를 사용해야 URL에 맵핑 경로가 남지 않는다.
	}
	
	/*
	@GetMapping("/list")
	public void list(Model m) {
		log.info("@@@@@ Get list Method Join Success");
		
		List<BoardVO> list = msv.getBvoList();
		if(list.size() > 0) {
			log.info("@@@@@ List<BoardVO> list @@@ Success");
		}
		
		m.addAttribute("list", list);
	}
	*/
	@GetMapping("/list")
	public void list(Model m, PagingVO pgvo) {
		log.info("@@@@@ Get list Method Join Success");
		log.info("@@@@@ Get list Method pgvo @@@ " + pgvo);
		
		List<BoardVO> list = bsv.getBvoList(pgvo);
		if(list.size() > 0) {
			log.info("@@@@@ List<BoardVO> list @@@ Success");
		}
		
		m.addAttribute("list", list);
		
		// totalCount 구하기 (pgvo는 검색할 때 필요)
		int totalCount = bsv.getTotalCount(pgvo);
		
		// PagingHandler 객체 생성
		PagingHandler ph = new PagingHandler(pgvo, totalCount);
		
		// PagingHandler 객체 보내기
		m.addAttribute("ph", ph);
	}
	
	@GetMapping("/detail")
	public void detail(@RequestParam("bno") long bno, Model m) {
		log.info("@@@@@ Get detail Method Join Success");
		
		BoardDTO bdto = bsv.getDetail(bno);
		m.addAttribute("bdto", bdto);
	}
	
	/*
	@PostMapping("/modify")
	public void modify(BoardVO bvo, Model m) {
		log.info("@@@@@ Post modify Method Join Success");
		
		m.addAttribute("bvo", bvo);
	}
	*/
	/*
	@PostMapping("/modify")
	public String modify(BoardVO bvo, RedirectAttributes re) {
		log.info("@@@@@ Post modify Method Join Success");
		
		int isOK = bsv.editBvo(bvo);
		log.info("@@@@@ editBvo @@@ " + (isOK > 0 ? "Success" : "Fail"));
		
		return "redirect:/board/detail?bno="+bvo.getBno();
	}
	*/
	@PostMapping("/modify")
	public String modify(BoardVO bvo, RedirectAttributes re,
			@RequestParam(name="files", required = false) MultipartFile[] files) {
		log.info("@@@@@ Post modify Method Join Success");
		
		List<FileVO> flist = null;
		if(files[0].getSize() > 0 || files != null) {
			flist = fh.uploadFiles(files);
		}
		
		int isOK = bsv.editBvo(new BoardDTO(bvo, flist));
		log.info("@@@@@ editBvo @@@ " + (isOK > 0 ? "Success" : "Fail"));
		
		return "redirect:/board/detail?bno="+bvo.getBno();
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("bno") long bno) {
		log.info("@@@@@ Get delete Method Join Success");
		
		int isOK = bsv.deleteBvo(bno);
		log.info("@@@@@ deleteBvo @@@ " + (isOK > 0 ? "Success" : "Fail"));
		
		return "redirect:/";
	}
	
	/*
	@PostMapping("/edit")
	public String edit(BoardVO bvo) {
		log.info("@@@@@ Post edit Method Join Success");
		
		int isOK = msv.editBvo(bvo);
		log.info("@@@@@ editBvo @@@ " + (isOK > 0 ? "Success" : "Fail"));
		
		return "redirect:/board/list";
	}
	*/

}
