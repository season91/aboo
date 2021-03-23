package com.kh.aboo.board.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.kh.aboo.board.model.vo.Board;

public interface BoardService {
	
	public void insertBoard(Board board, List<MultipartFile> files);

	public Map<String,Object> selectBoardList(int currentPage);
	
	public Map<String, Object> selectBoardDetail(String bdIdx);
	
	}
