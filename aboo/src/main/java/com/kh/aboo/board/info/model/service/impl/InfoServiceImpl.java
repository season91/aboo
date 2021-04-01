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
import com.kh.aboo.board.info.model.vo.InfoCmt;
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
	public int insertInfoBoard(InfoBoard infoBoard) {
		int res=infoRepository.insertInfoBoard(infoBoard);

		
		return res;
		
	}

	@Override
	public Map<String, Object> selectInfoBoardList(int currentPage,String apartmentIdx) {

		
		Paging paging = Paging.builder()
				.currentPage(currentPage)
				.blockCnt(5)
				.cntPerPage(10)
				.type("info")
				.total(infoRepository.selectInfoContentCnt(apartmentIdx))
				.build();
		System.out.println(paging.toString());
	
		Map<String,Object> commandMap = new HashMap<String, Object>();
		commandMap.put("paging", paging);
		commandMap.put("infoBoard", infoRepository.selectInfoBoardList(paging.getQueryStart(), paging.getQueryEnd(),apartmentIdx));
					
		return commandMap;

	}

	@Override
	public Map<String, Object> selectInfoBoardDetail(String bIdx) {

		InfoBoard infoBoard = infoRepository.selectInfoBoardDetail(bIdx);
		List<FileVo> files = infoRepository.selectFileWithBIdx(bIdx);
		
		Map<String,Object> commandMap = new HashMap<String, Object>();
		commandMap.put("infoBoard",infoBoard);
		commandMap.put("files", files);

		return  commandMap;
	}

	@Override
	public int updateInfoBoard(InfoBoard infoBoard) {
		
		int res = infoRepository.updateInfoBoard(infoBoard);
			
		return res;
	}

	@Override
	public int deleteInfoBoard(String bIdx, String apartmentIdx) {
		
		return infoRepository.deleteInfoBoard(bIdx, apartmentIdx);
	}

	@Override
	public int updateInfoPrivate(String bIdx) {
		
		return infoRepository.updateInfoPrivate(bIdx);
	}

	@Override
	public int insertInfoCmt(InfoCmt infoCmt) {
		
		return infoRepository.insertInfoCmt(infoCmt);
	}

	@Override
	public List<InfoCmt> selectInfoCmtList(String bIdx) {
		
		return infoRepository.selectInfoCmtList(bIdx);
	}

	@Override
	public int selectInfoCmtcnt(String bIdx) {
		
		return infoRepository.selectInfoCmtcnt(bIdx);
	}

	@Override
	public int updateInfoCmt(InfoCmt infoCmt) {

		return infoRepository.updateInfoCmt(infoCmt);
	}

	@Override
	public int deleteInfoCmt(String cIdx) {
		
		return infoRepository.deleteInfoCmt(cIdx);
	}

	@Override
	public int UpdateInfoCmtprivate(String cIdx) {
		
		return infoRepository.UpdateInfoCmtprivate(cIdx);
	}

}
