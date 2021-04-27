package com.kh.aboo.board.used.model.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kh.aboo.board.used.model.repository.UsedCmtRepository;
import com.kh.aboo.board.used.model.repository.UsedRepository;
import com.kh.aboo.board.used.model.service.UsedService;
import com.kh.aboo.board.used.model.vo.UsedBrd;
import com.kh.aboo.board.used.model.vo.UsedCmt;
import com.kh.aboo.common.code.ErrorCode;
import com.kh.aboo.common.exception.ToAlertException;
import com.kh.aboo.common.util.file.FileUtil;
import com.kh.aboo.common.util.file.FileVo;
import com.kh.aboo.common.util.paging.Paging;
import com.kh.aboo.user.generation.model.vo.Generation;

@Service
public class UsedServiceImpl implements UsedService {

	private final UsedRepository usedRepository;
	private final UsedCmtRepository usedCmtRepository;

	public UsedServiceImpl(UsedRepository usedRepository, UsedCmtRepository usedCmtRepository) {
		this.usedRepository = usedRepository;
		this.usedCmtRepository = usedCmtRepository;
	}

	public Map<String, Object> searchMap(String apartmentIdx, String kind, String keyword) {
		Map<String, Object> searchMap = new HashMap<String, Object>();

		searchMap.put("apartmentIdx", apartmentIdx);
		searchMap.put("keyword", keyword);

		switch (kind) {
		case "apartmentIdx":
			// 기본 페이징
			searchMap.put("searchType", "apartmentIdx");
			break;
		case "search":
			// 키워드로 검색
			searchMap.put("searchType", "search");
			break;
		case "trnsc":
			// 키워드로 검색
			searchMap.put("searchType", "trnsc");
			break;

		}

		return searchMap;
	}

	@Override
	public Map<String, Object> selectUsedBrdList(int currentPage, String apartmentIdx, String kind, String keyword) {
		Map<String, Object> searchMap = searchMap(apartmentIdx, kind, keyword);

		Paging paging = Paging.builder().currentPage(currentPage).blockCnt(5).cntPerPage(6).type("board")
				.total(usedRepository.selectUsedBrdCnt(searchMap)).build();

		searchMap.put("paging", paging);

		List<UsedBrd> usedBrdList = usedRepository.selectUsedBrdList(searchMap);
		List<FileVo> fileList = new ArrayList<FileVo>();
		List<Integer> cmtList = new ArrayList<Integer>();

		for (UsedBrd usedBrd : usedBrdList) {
			fileList.add(usedRepository.selectFileWithusedIdx(usedBrd.getUsedIdx())); // null이 오는데 add에 왜담김 ??;;
			cmtList.add(usedCmtRepository.selectUsedBrdCmtCnt(usedBrd.getUsedIdx()));
			System.out.println("게시판" + usedBrd.getUsedIdx());
			for (FileVo fileVo : fileList) {
				System.out.println("파일" + fileVo.getTypeIdx());

			}
		}

		searchMap.put("fileList", fileList);
		searchMap.put("cmtList", cmtList);
		searchMap.put("usedBrdList", usedRepository.selectUsedBrdList(searchMap));
		return searchMap;
	}

	@Override
	public Map<String, Object> selectUsedDetail(String usedIdx) {
		UsedBrd usedBrd = usedRepository.selectUsedDetail(usedIdx);
		FileVo files = usedRepository.selectFileWithusedIdx(usedIdx);

		Map<String, Object> commandMap = new HashMap<String, Object>();
		commandMap.put("UsedBrd", usedBrd);
		commandMap.put("files", files);

		return commandMap;
	}

	@Override
	public int updateUsedPrivate(String usedIdx) {
		return usedRepository.updateUsedPrivate(usedIdx);
	}

	@Override
	public void updateUsedDelete(String usedIdx) {
		usedRepository.updateUsedDelete(usedIdx);
		usedRepository.updateUsedFileDelete(usedIdx);
	}

	@Override
	public UsedBrd selectUsedIdx(String usedIdx) {
		return usedRepository.selectUsedIdx(usedIdx);
	}

	@Override
	public void insertUsedBrd(UsedBrd usedBrd, List<MultipartFile> files) {

		FileUtil fileUtil = new FileUtil();
		usedRepository.insertUsedBrd(usedBrd); // 게시물 추가

		try {
			List<FileVo> fiList = fileUtil.fileUpload(files);

			for (FileVo fileVo : fiList) {
				usedRepository.insertUsedBrdFile(fileVo);
			}
		} catch (Exception e) {
			throw new ToAlertException(ErrorCode.IB01, e);

		}

	}

	@Override
	public void updateUsedBrdFileModify(UsedBrd usedBrd, List<MultipartFile> files) {

		FileUtil fileUtil = new FileUtil();
		Map<String, Object> commandMap = new HashedMap<>();

		usedRepository.updateUsedBrdModify(usedBrd);

		try {
			List<FileVo> fiList = fileUtil.fileUpload(files);
			commandMap.put("usedIdx", usedBrd.getUsedIdx()); // 게시물 번호

			for (FileVo fileVo : fiList) {
				System.out.println("파일이 도나염 ? @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@" + fileVo);
				commandMap.put("fileVo", fileVo);
				usedRepository.updateUsedBrdFileModify(commandMap); // 게시물 사진
			}

		} catch (Exception e) {
			throw new ToAlertException(ErrorCode.IB01, e);
		}
	}

	@Override
	public int insertUsedBrdCmtUpload(UsedCmt usedCmt) {
		return usedCmtRepository.insertUsedBrdCmtUpload(usedCmt);
	}

	@Override
	public List<UsedCmt> selectUsedBrdCmt(String usedIdx) {
		return usedCmtRepository.selectUsedBrdCmt(usedIdx);
	}

	@Override
	public int selectUsedBrdCmtCnt(String usedIdx) {
		return usedCmtRepository.selectUsedBrdCmtCnt(usedIdx);
	}

	@Override
	public int updateUsedBrdCmt(UsedCmt usedCmt) {
		return usedCmtRepository.updateUsedBrdCmt(usedCmt);
	}

	@Override
	public int updateUsedBrdCmtDelete(String usedCmtIdx) {
		return usedCmtRepository.updateUsedBrdCmtDelete(usedCmtIdx);
	}

	@Override
	public int updateUsedBrdCmtPrivate(String usedCmtIdx) {
		return usedCmtRepository.updateUsedBrdCmtPrivate(usedCmtIdx);
	}

	public int selectUsedBrdTodayCnt(String apartmentIdx) {
		return usedRepository.selectUsedBrdTodayCnt(apartmentIdx);
	}

	public int selectInfoBrdTodayCnt(String apartmentIdx) {
		return usedRepository.selectInfoBrdTodayCnt(apartmentIdx);
	}
	
	public int selectIntBrdTodayCnt(String apartmentIdx) {
		return usedRepository.selectIntBrdTodayCnt(apartmentIdx);
	}


}
