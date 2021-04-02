package com.kh.aboo.board.used.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;

import com.kh.aboo.board.used.model.service.UsedService;
import com.kh.aboo.board.used.model.vo.UsedBrd;
import com.kh.aboo.board.used.model.vo.UsedCmt;
import com.kh.aboo.user.generation.model.vo.Generation;
import com.kh.aboo.user.manager.model.vo.Admin;
import com.sun.org.apache.xpath.internal.operations.Mod;

@RequestMapping("board/used")
@Controller
public class UsedController {

	private final UsedService usedService;

	public UsedController(UsedService usedService) {
		this.usedService = usedService;
	}

	@GetMapping("usedlist")
	public String usedList(@RequestParam(defaultValue = "1") int page, Model model, HttpSession session) {

		Generation generation = (Generation) session.getAttribute("generation");
		Admin admin = (Admin) session.getAttribute("admin");

		if (generation != null) {
			model.addAllAttributes(usedService.selectUsedBrdList(page, generation.getApartmentIdx()));
		} else {
			model.addAllAttributes(usedService.selectUsedBrdList(page, admin.getApartmentIdx()));

		}

		return "board/used/usedList";
	}

	@GetMapping("useddetail")
	public String usedDetail(String usedIdx, Model model) {

		model.addAllAttributes(usedService.selectUsedDetail(usedIdx));
		model.addAttribute("usedBrdCmtList", usedService.selectUsedBrdCmt(usedIdx));
		model.addAttribute("usedBrdCmtCnt", usedService.selectUsedBrdCmtCnt(usedIdx));
		return "board/used/usedDetail";

	}

	@GetMapping("usedprivate")
	@ResponseBody
	public String usedPrivate(String usedIdx, Model model) {

		int res = usedService.updateUsedPrivate(usedIdx);

		if (res > 0) {
			return "success";
		}

		return "fail";
	}

	@GetMapping("useddelete")
	@ResponseBody
	public String usedDelete(String usedIdx, Model model) {

		usedService.updateUsedDelete(usedIdx);

		return "success";

	}

	@GetMapping("usedmodify")
	public String usedModify(String usedIdx, Model model) {

		UsedBrd usedBrd = usedService.selectUsedIdx(usedIdx);
		String ctnt = usedBrd.getUsedContent().replace(System.getProperty("line.separator").toString(), "");

		System.out.println(usedBrd);

		model.addAttribute("usedBrd", usedBrd);
		model.addAttribute("ctnt", ctnt);

		return "board/used/usedModify";
	}

	@PostMapping("usedmodifyimpl")
	public String usedmodifyImpl(@RequestParam List<MultipartFile> files, UsedBrd usedBrd, Model model) {

		usedService.updateUsedBrdFileModify(usedBrd, files);

		model.addAttribute("alertMsg", "게시물 수정에 성공하였습니다.");
		model.addAttribute("url", "/board/used/usedlist");
		return "common/result";
	}

	@GetMapping("usedupload")
	public String usedUpload() {
		return "board/used/usedUpload";
	}

	@PostMapping("useduploadimpl")
	public String usedUploadimpl(@RequestParam List<MultipartFile> files, UsedBrd usedBrd,
			@SessionAttribute(name = "generation", required = false) Generation generation, Model model) {

		String generationIdx = generation.getGenerationIdx();
		String apartmentIdx = generation.getApartmentIdx();
		String usedWriter = generation.getBuilding() + "동" + generation.getNum() + "호";
		usedBrd.setUsedWriter(usedWriter); // 닉네임 처럼 게시판 작성자
		usedBrd.setGenerationIdx(generationIdx);
		usedBrd.setApartmentIdx(apartmentIdx);

		usedService.insertUsedBrd(usedBrd, files);

		model.addAttribute("alertMsg", "게시물 등록에 성공하였습니다.");
		model.addAttribute("url", "/board/used/usedlist");
		return "common/result";
	}

	@PostMapping("usedcmtupload")
	public String usedCmtUpload(UsedCmt usedCmt,
			@SessionAttribute(name = "generation", required = false) Generation generation, Model model) {

		String usedCmtWriter = generation.getBuilding() + "동" + generation.getNum() + "호";
		usedCmt.setUsedCmtWriter(usedCmtWriter);
		usedService.insertUsedBrdCmtUpload(usedCmt);

		model.addAttribute("alertMsg", "댓글 등록에 성공하였습니다.");
		model.addAttribute("url", "/board/used/useddetail?usedIdx=" + usedCmt.getUsedIdx());
		return "common/result";

	}

	@PostMapping("usedbrdcmtmodify")
	public String usedBrdCmtModify(UsedCmt usedCmt, UsedBrd usedBrd, Model model) {

		usedService.updateUsedBrdCmt(usedCmt);

		model.addAttribute("alertMsg", "댓글 수정에 성공하였습니다.");
		model.addAttribute("url", "/board/used/useddetail?usedIdx=" + usedCmt.getUsedIdx());
		return "common/result";
	}

	@GetMapping("usedbrdcmtdelete")
	@ResponseBody
	public String usedBrdCmtDelete(String usedCmtIdx, Model model) {

		int res = usedService.updateUsedBrdCmtDelete(usedCmtIdx);

		if (res > 0) {

			return "success";
		} else {
			return "fail";
		}

	}

	@GetMapping("usedbrdcmtprivate")
	@ResponseBody
	public String usedBrdCmtPrivate(String usedCmtIdx, Model model) {

		int res = usedService.updateUsedBrdCmtPrivate(usedCmtIdx);

		if (res > 0) {

			return "success";

		} else {
			return "fail";
		}

	}

}
