package com.kh.aboo.board.info.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
	
	@ResponseBody
	@PostMapping("upload")
	public String uploadBoard(
			@RequestParam List<MultipartFile> files
			,InfoBoard infoBoard
			,@SessionAttribute(name="generation", required = false)
			Generation generation
			) {
		
		//System.out.println("multipartFile list length : " + files.size());
		//System.out.println(files.get(0));
		
		System.out.println("여긴오나?");
		System.out.println(infoBoard.getbCategory());
	
		String apartmentIdx = generation.getApartmentIdx();
		String bWriter = generation.getId();
		infoBoard.setApartmentIdx(apartmentIdx);
		infoBoard.setbWriter(bWriter);
		
		
		infoService.insertInfoBoard(infoBoard, files);
		//  /index url로 redirect 요청
<<<<<<< Updated upstream
		return "board/info/listinfo";
=======
		return "/listinfo";
>>>>>>> Stashed changes
	}
	
	//희원
	@GetMapping("editinfo")
	public void editInfo() {};
	
	
}
