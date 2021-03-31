package com.kh.aboo.board.used.model.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kh.aboo.board.used.model.repository.UsedRepository;
import com.kh.aboo.board.used.model.service.UsedService;
import com.kh.aboo.board.used.model.vo.UsedBrd;
import com.kh.aboo.common.code.ErrorCode;
import com.kh.aboo.common.exception.ToAlertException;
import com.kh.aboo.common.util.file.FileUtil;
import com.kh.aboo.common.util.file.FileVo;
import com.kh.aboo.common.util.paging.Paging;

@Service
public class UsedServiceImpl implements UsedService {

	private final UsedRepository usedRepository;

	public UsedServiceImpl(UsedRepository usedRepository) {
		this.usedRepository = usedRepository;
	}

	@Override
	public Map<String, Object> selectUsedBrdList(int currentPage, String apartmentIdx) {
		Paging paging = Paging.builder().currentPage(currentPage).blockCnt(5).cntPerPage(6).type("board")
				.total(usedRepository.selectUsedBrdCnt()).build();

		Map<String, Object> commandMap = new HashMap<String, Object>();
		Map<String, Object> usedMap = new HashMap<String, Object>();

		usedMap.put("paging", paging);
		usedMap.put("apartmentIdx", apartmentIdx);

		commandMap.put("paging", paging);
		commandMap.put("usedBrdList", usedRepository.selectUsedBrdList(usedMap));

		return commandMap;
	}

	@Override
	public Map<String, Object> selectUsedDetail(String usedIdx) {
		UsedBrd usedBrd = usedRepository.selectUsedDetail(usedIdx);
		List<FileVo> files = usedRepository.selectFileWithusedIdx(usedIdx);
	
		Map<String,Object> commandMap = new HashMap<String, Object>();
		commandMap.put("UsedBrd",usedBrd);
		commandMap.put("files", files);
		
		return commandMap;
	}

	@Override
	public int updateUsedPrivate(String usedIdx) {
		return usedRepository.updateUsedPrivate(usedIdx);
	}

	@Override
	public int updateUsedDelete(String usedIdx) {
		return usedRepository.updateUsedDelete(usedIdx);
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

			System.out.println("fiList" + fiList);
			for (FileVo fileVo : fiList) {
				usedRepository.insertUsedBrdFile(fileVo);
			}
		} catch (Exception e) {
			throw new ToAlertException(ErrorCode.IB01, e);

		}

	}
}
