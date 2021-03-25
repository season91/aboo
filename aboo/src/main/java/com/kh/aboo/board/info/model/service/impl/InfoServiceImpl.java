package com.kh.aboo.board.info.model.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kh.aboo.board.info.model.repository.InfoRepository;
import com.kh.aboo.board.info.model.service.InfoService;
import com.kh.aboo.board.info.model.vo.InfoBoard;
import com.kh.aboo.common.code.ErrorCode;
import com.kh.aboo.common.exception.ToAlertException;
import com.kh.aboo.common.util.file.FileUtil;
import com.kh.aboo.common.util.file.FileVo;

@Service
public class InfoServiceImpl implements InfoService{
	
	private final InfoRepository infoRepository;
	
	public InfoServiceImpl(InfoRepository infoRepository) {
		this.infoRepository = infoRepository;
	}

	@Override
	public void insertInfoBoard(InfoBoard infoBoard, List<MultipartFile> files) {
		
		FileUtil fileUtil = new FileUtil();
		infoRepository.insertInfoBoard(infoBoard);
		
		try {
			
			List<FileVo> fileList = fileUtil.fileUpload(files);
			for (FileVo fileVO : fileList) {

			}
		} catch (Exception e) {
			throw new ToAlertException(ErrorCode.IB01,e);
		}
		
	}

}
