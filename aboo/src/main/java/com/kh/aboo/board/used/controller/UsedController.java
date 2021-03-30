package com.kh.aboo.board.used.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.kh.aboo.board.model.repository.BoardRepository;
import com.kh.aboo.board.model.service.BoardService;
import com.kh.aboo.board.used.model.service.UsedService;
import com.kh.aboo.board.used.model.vo.UsedBrd;
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
			System.out
					.println(model.addAllAttributes(usedService.selectUsedBrdList(page, generation.getApartmentIdx())));

		} else {
			model.addAllAttributes(usedService.selectUsedBrdList(page, admin.getApartmentIdx()));
			System.out.println(model.addAllAttributes(usedService.selectUsedBrdList(page, admin.getApartmentIdx())));

		}

		return "board/used/usedList";
	}

	@GetMapping("useddetail")
	public String usedDetail(String usedIdx, Model model) {

		System.out.println(usedService.selectUsedDetail(usedIdx));
		UsedBrd usedBrd = usedService.selectUsedDetail(usedIdx);
		model.addAttribute("UsedBrd", usedBrd);

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

		int res = usedService.updateUsedDelete(usedIdx);

		if (res > 0) {
			return "success";
		}

		return "fail";
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
	public String usedmodifyImpl(UsedBrd usedBrdInfo, Model model) {
		System.out.println(usedBrdInfo);

		return "";
	}

}
