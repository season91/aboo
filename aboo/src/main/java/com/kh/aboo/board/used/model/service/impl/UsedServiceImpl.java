package com.kh.aboo.board.used.model.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

@Service
public class UsedServiceImpl implements UsedService {

	private final UsedRepository usedRepository;
	private final UsedCmtRepository usedCmtRepository;
	
	public UsedServiceImpl(UsedRepository usedRepository, UsedCmtRepository usedCmtRepository) {
		this.usedRepository = usedRepository;
		this.usedCmtRepository = usedCmtRepository;
	}

	@Override
	public Map<String, Object> selectUsedBrdList(int currentPage, String apartmentIdx) {
		Paging paging = Paging.builder().currentPage(currentPage).blockCnt(5).cntPerPage(6).type("board")
				.total(usedRepository.selectUsedBrdCnt(apartmentIdx)).build();

		Map<String, Object> commandMap = new HashMap<String, Object>();
		Map<String, Object> usedMap = new HashMap<String, Object>();

		usedMap.put("paging", paging);
		usedMap.put("apartmentIdx", apartmentIdx);

		List<UsedBrd> usedBrdList = usedRepository.selectUsedBrdList(usedMap);
		List<FileVo> fileList = new ArrayList<FileVo>();

		for (UsedBrd usedBrd : usedBrdList) {
			fileList.add(usedRepository.selectFileWithusedIdx(usedBrd.getUsedIdx())); // null이 오는데 add에 왜담김 ??;;
			System.out.println("게시판"+ usedBrd.getUsedIdx());
			for (FileVo fileVo : fileList) {
				System.out.println("파일"+ fileVo.getTypeIdx());

			}
		}

		commandMap.put("paging", paging);
		commandMap.put("fileList", fileList);
		commandMap.put("usedBrdList", usedRepository.selectUsedBrdList(usedMap));
		return commandMap;
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
		Map<String,Object> commandMap = new HashedMap<>();
		
		usedRepository.updateUsedBrdModify(usedBrd);
		

		try {
			List<FileVo> fiList = fileUtil.fileUpload(files);
			commandMap.put("usedIdx",usedBrd.getUsedIdx()); //게시물 번호
			
			for (FileVo fileVo : fiList) {
				System.out.println("파일이 도나염 ? @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@" + fileVo);
				commandMap.put("fileVo", fileVo);
				usedRepository.updateUsedBrdFileModify(commandMap); //게시물 사진
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
}
