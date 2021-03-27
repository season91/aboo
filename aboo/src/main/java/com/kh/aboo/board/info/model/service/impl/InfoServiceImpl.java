package com.kh.aboo.board.info.model.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.kh.aboo.board.info.model.repository.InfoRepository;
import com.kh.aboo.board.info.model.service.InfoService;
import com.kh.aboo.board.info.model.vo.InfoBoard;
import com.kh.aboo.common.code.ErrorCode;
import com.kh.aboo.common.exception.ToAlertException;
import com.kh.aboo.common.util.file.FileUtil;
import com.kh.aboo.common.util.file.FileVo;
import com.kh.aboo.common.util.paging.Paging;

@Service
public class InfoServiceImpl implements InfoService{
	
	private final InfoRepository infoRepository;
	
	public InfoServiceImpl(InfoRepository infoRepository) {
		this.infoRepository = infoRepository;
	}

	@Override
	@Transactional
	public int insertInfoBoard(InfoBoard infoBoard, List<MultipartFile> files) {
		
		FileUtil fileUtil = new FileUtil();
		int res=infoRepository.insertInfoBoard(infoBoard);

		
		try {
			
			List<FileVo> fileList = fileUtil.fileUpload(files);
			for (FileVo fileVo : fileList) {
				infoRepository.insertFile(fileVo);
			}
		} catch (Exception e) {
			throw new ToAlertException(ErrorCode.IB01,e);
		}
		
		return res;
		
	}

	@Override
	public Map<String, Object> selectInfoBoardList(int currentPage) {

		
		Paging paging = Paging.builder()
				.currentPage(currentPage)
				.blockCnt(5)
				.cntPerPage(10)
				.type("infoBoard")
				.total(infoRepository.selectInfoContentCnt())
				.build();
		System.out.println(paging.toString());
	
		Map<String,Object> commandMap = new HashMap<String, Object>();
		commandMap.put("paging", paging);
		commandMap.put("infoBoard", infoRepository.selectInfoBoardList(paging));
					
		return commandMap;

	}

	@Override
	public Map<String, Object> selectInfoBoardDetail(String bIdx) {

		InfoBoard infoBoard = infoRepository.selectInfoBoardDetail(bIdx);
		List<FileVo> files = infoRepository.selectFileWithBdIdx(bIdx);
		
		Map<String,Object> commandMap = new HashMap<String, Object>();
		commandMap.put("infoBoard",infoBoard);
		commandMap.put("files", files);

		return null;
	}

}