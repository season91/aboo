package com.kh.aboo.board.info.controller;

import java.util.List;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;

import com.kh.aboo.board.info.model.service.InfoService;
import com.kh.aboo.board.info.model.vo.InfoBoard;
import com.kh.aboo.user.generation.model.vo.Generation;

@Controller
@RequestMapping("board/info")
public class InfoController {
	
	private final InfoService infoService;
	
	public InfoController(InfoService infoService) {
		this.infoService = infoService;
	}

	//희원
	@GetMapping("listinfo")
	public void listInfo() {};
	
	//희원
	@GetMapping("detailinfo")
	public void detailInfo() {};
	
	//희원
	@GetMapping("addinfo")
	public void addInfo() {};
	
	@PostMapping("upload")
	public String uploadBoard(
			@RequestParam List<MultipartFile> files
			,InfoBoard infoBoard
			,@SessionAttribute(name="generation", required = false)
			Generation generation,
			Model model
			) {
		
		//System.out.println("multipartFile list length : " + files.size());
		//System.out.println(files.get(0));
	
		String apartmentIdx = generation.getApartmentIdx();
		String bWriter = generation.getId();
		infoBoard.setApartmentIdx(apartmentIdx);
		infoBoard.setbWriter(bWriter);
		
		int res = infoService.insertInfoBoard(infoBoard, files);
		if(res > 0) {
			model.addAttribute("alertMsg", "게시물이 등록되었습니다.");
			model.addAttribute("url", "/board/info/listinfo");
		}else {
			model.addAttribute("alertMsg", "게시물이 등록 도중 에러가 발생했습니다.");
			model.addAttribute("url", "/board/info/listinfo");
		}
		
		return "common/result";
	}
	
	//희원
	@GetMapping("editinfo")
	public void editInfo() {};
	
	
}
