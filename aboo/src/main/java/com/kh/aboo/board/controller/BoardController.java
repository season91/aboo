package com.kh.aboo.board.controller;

import java.nio.charset.Charset;
import java.util.List;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;

import com.kh.aboo.board.model.service.BoardService;
import com.kh.aboo.board.model.vo.Board;
import com.kh.aboo.member.model.vo.Member;

import common.util.file.FileVo;

@Controller
@RequestMapping("board")
public class BoardController {
	
	private final BoardService boardService;
	
	public BoardController(BoardService boardService) {
		this.boardService = boardService;
	}

	//로그인 하면 member가 있고 안하면 없을것 임
	@PostMapping("upload")
	public String uploadBoard(@RequestParam List<MultipartFile> files, Board board, 
			@SessionAttribute(name="userInfo", required = false) Member member) {
		
		// 로그인 회원이라면
		String userId = member == null?"guest":member.getUserId();
		board.setUserId(userId);
		// MultipartFile 리스트가 날라갈거고,
		
		boardService.insertBoard(board, files);
		
		// redirect: 의미 - /index 로 바로 redirect 요청.
		return "redirect:/index";
	}
	
	@GetMapping("boardForm")
	public void boardForm() {};
	
	
	// 현재페이지 받아 페이징객체 생성해 서비스로 넘긴다.
	@GetMapping("list")
	public String boardList(@RequestParam(defaultValue = "1")int page, Model model){
		model.addAllAttributes(boardService.selectBoardList(page));
		return "board/boardList";
	}
	
	@GetMapping("detail")
	public String boardDetail(String bdIdx, Model model) {
		model.addAllAttributes(boardService.selectBoardDetail(bdIdx));
		return "board/boardView";
	}
	
	
	@GetMapping("download")
	public ResponseEntity<FileSystemResource> downloadFile(FileVo file){
		// ResponseEntity 꾸리기. 헤더와 바디.
		// http header : content-disposition : attachment, fileName=test
		// 헤더내용
		HttpHeaders headers= new HttpHeaders();
		headers.setContentDisposition(ContentDisposition.builder("attachment").filename(file.getOriginFileName(), Charset.forName("UTF-8"))
				.build());
		// 바디내용 : 저장파일 전체경로로 
		FileSystemResource resource = new FileSystemResource(file.getFullPath() + file.getRenameFileName());
		
		// 헤더와 바디 넣어주기.
		return ResponseEntity.ok().headers(headers).body(resource);
		
	}
	
	@GetMapping("info/listinfo")
	public void listInfo() {};
	
	@GetMapping("info/detailinfo")
	public void detailInfo() {};
	
	@GetMapping("info/addinfo")
	public void addInfo() {};
	
	@GetMapping("info/editinfo")
	public void editInfo() {};
	
	
	
}
