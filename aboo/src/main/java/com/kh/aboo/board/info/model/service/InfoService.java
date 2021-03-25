package com.kh.aboo.board.info.model.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.kh.aboo.board.info.model.vo.InfoBoard;

public interface InfoService {
	
	//희원 정보게시판 게시글 업로드
	public void insertInfoBoard (InfoBoard infoBoard, List<MultipartFile> files );

}
