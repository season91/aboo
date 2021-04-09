package com.kh.aboo.board.info.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;

import com.kh.aboo.board.info.model.service.InfoService;
import com.kh.aboo.board.info.model.vo.InfoBoard;
import com.kh.aboo.board.info.model.vo.InfoCmt;
import com.kh.aboo.user.generation.model.vo.Generation;
import com.kh.aboo.user.manager.model.vo.Admin;

@Controller
@RequestMapping("board/info")
public class InfoController {
	
	private final InfoService infoService;
	
	public InfoController(InfoService infoService) {
		this.infoService = infoService;
	}

	//희원 게시글 목록 페이지로 이동
	@GetMapping("listinfo")
	public String listInfo(@RequestParam(defaultValue = "1") int page,Model model,HttpSession session) {
		
		Generation generation = (Generation) session.getAttribute("generation");
		Admin admin = (Admin) session.getAttribute("admin");
		
		if(admin != null) {
			
			String apartmentIdx = admin.getApartmentIdx();
			model.addAllAttributes(infoService.selectInfoBoardList(page, apartmentIdx));

		}else {
			
			String apartmentIdx = generation.getApartmentIdx();
			model.addAllAttributes(infoService.selectInfoBoardList(page,apartmentIdx));
	
		}
		
		return "board/info/listinfo";

	}
	
	@GetMapping("search")
	public String searchInfo(@RequestParam(defaultValue = "1") int page,String keyword,Model model,HttpSession session) {
		
		Generation generation = (Generation) session.getAttribute("generation");
		Admin admin = (Admin) session.getAttribute("admin");
		session.setAttribute("keyword", keyword);
		
		if(admin != null) {
			
			String apartmentIdx = admin.getApartmentIdx();
			model.addAllAttributes(infoService.selectInfoSearchList(page, apartmentIdx, keyword));

			
			
		}else {
			
			String apartmentIdx = generation.getApartmentIdx();
			model.addAllAttributes(infoService.selectInfoBoardList(page,apartmentIdx));

				model.addAllAttributes(infoService.selectInfoSearchList(page, apartmentIdx, keyword));

		}
		
		return "board/info/searchinfo";

	}
	
	//희원 게시글 상세페이지로 이동
	@GetMapping("detail")
	public String detailInfo(String bIdx, Model model) {

		//해당 글번호의 상세페이지 불러오기
		model.addAllAttributes(infoService.selectInfoBoardDetail(bIdx));
		//해당 글번호의 댓글리스트 불러오기
		model.addAttribute("infoCmtList",infoService.selectInfoCmtList(bIdx));
		//해당 글번호의 댓글 수 불러오기
		model.addAttribute("infoCmtCnt",infoService.selectInfoCmtcnt(bIdx));
		
		
		return "board/info/detailinfo";
		
	};
	
	//희원 글쓰기 페이지로 이동
	@GetMapping("addinfo")
	public void addInfo() {};
	
	//희원 게시글 업로드
	@PostMapping("upload")
	public String uploadBoard(InfoBoard infoBoard
			,@SessionAttribute(name="generation", required = false)
			Generation generation,
			Model model
			) {
		
		//System.out.println("multipartFile list length : " + files.size());
		//System.out.println(files.get(0));
	
		//session에서 아파트번호와 세대번호 불러오기
		String apartmentIdx = generation.getApartmentIdx();
		String generationIdx = generation.getGenerationIdx();
		infoBoard.setApartmentIdx(apartmentIdx);
		infoBoard.setbWriter(generation.getBuilding() + "동 " + generation.getNum() + "호");
		infoBoard.setGenerationIdx(generationIdx);
		
		Document doc = Jsoup.parse(infoBoard.getbContent());
		Elements img = doc.select("img");
		
		List<String> imgs = new ArrayList<>();
		for (Element element : img) {
			Node node = element;
			String imgUrl = node.attr("src");
			imgs.add(imgUrl);
		}
		
		int res = infoService.insertInfoBoard(infoBoard);
		if(res > 0) {
			model.addAttribute("alertMsg", "게시물이 등록되었습니다.");
			model.addAttribute("url", "/board/info/listinfo");
		}else {
			model.addAttribute("alertMsg", "게시물이 등록 도중 에러가 발생했습니다.");
			model.addAttribute("url", "/board/info/listinfo");
		}
		
		return "common/result";
	}
	
	//희원 게시글 수정화면으로 이동
	@GetMapping("editinfo")
	public String editInfo(String bIdx, Model model) {
		

		model.addAllAttributes(infoService.selectInfoBoardDetail(bIdx));
		return "board/info/editinfo";
	};
	
	//게시글 삭제
	@PostMapping("editimpl")
	public String editimpl(@RequestParam List<MultipartFile> files
			,InfoBoard infoBoard, String bIdx, Model model) {
		
		infoBoard.setbIdx(bIdx);
		
		int res = infoService.updateInfoBoard(infoBoard);

		if(res > 0) {
			model.addAttribute("alertMsg", "게시물이 수정되었습니다.");
			model.addAttribute("url", "/board/info/detail?bIdx=" + infoBoard.getbIdx());
		}else {
			model.addAttribute("alertMsg", "게시물을 수정하는 도중 에러가 발생했습니다.");
			model.addAttribute("url", "/board/info/editinfo?bIdx=" + infoBoard.getbIdx());
		}
		
		return "common/result";
	}
	
	//게시글 삭제
	@GetMapping("deleteinfo")
	@ResponseBody
	public String deleteInfo(String bIdx,@SessionAttribute(name="generation", required = false)Generation generation) {
		
		System.out.println(bIdx);
		System.out.println(generation.getApartmentIdx());
		
		int res = infoService.deleteInfoBoard(bIdx, generation.getApartmentIdx());
		
		if(res>0) {
			return "success";		
		}else {
			return "fail";
		}
		
	}
	
	//게시글 비공개 처리
	@GetMapping("privateinfo")
	@ResponseBody
	public String updateInfoPrivate(String bIdx) {
		
		int res = infoService.updateInfoPrivate(bIdx);
		
		if(res>0) {
			return "success";		
		}else {
			return "fail";
		}
		
	}
	
	//정보게시판 댓글 구현
	
	//댓글 추가하기
	@PostMapping("uploadinfocmt")
	public String uploadInfoCmt(InfoCmt infoCmt
			,@SessionAttribute(name="generation", required = false)
			Generation generation,
			Model model
			) {
		
		infoCmt.setcWriter(generation.getBuilding() + "동 " + generation.getNum() + "호");
		int res = infoService.insertInfoCmt(infoCmt);
		
		if(res > 0) {
			model.addAttribute("alertMsg", "댓글이 등록되었습니다.");
			model.addAttribute("url", "/board/info/detail?bIdx=" + infoCmt.getbIdx());
		}else {
			model.addAttribute("alertMsg", "댓글 등록에 실패하였습니다.");
			model.addAttribute("url", "/board/info/detail?bIdx=" + infoCmt.getbIdx());
		}
		
		return "common/result";
		
	}

	//댓글 수정하기
	@PostMapping("infocmtedit")
	public String infoCmtEdit(InfoCmt infoCmt, Model model) {
		
		int res = infoService.updateInfoCmt(infoCmt);
		
		if(res > 0) {
			model.addAttribute("alertMsg", "댓글이 수정되었습니다.");
			model.addAttribute("url", "/board/info/detail?bIdx=" + infoCmt.getbIdx());
		}else {
			model.addAttribute("alertMsg", "댓글 수정에 실패하였습니다.");
			model.addAttribute("url", "/board/info/detail?bIdx=" + infoCmt.getbIdx());
		}
		
		return "common/result";
	}
	
	//댓글 삭제하기
	@GetMapping("infocmtdel")
	@ResponseBody
	public String infoCmtDel(String cIdx) {
		
		System.out.println(cIdx);
		
		int res = infoService.deleteInfoCmt(cIdx);
		
		if(res>0) {
			return "success";		
		}else {
			return "fail";
		}
	}
	
	//댓글 비공개처리
	@GetMapping("infocmtprivate")
	@ResponseBody
	public String infoCmtPrivate(String cIdx) {
		
		int res = infoService.UpdateInfoCmtprivate(cIdx);
		
		if(res>0) {
			return "success";		
		}else {
			return "fail";
		}
		
	}
	
	
}
