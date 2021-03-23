package com.kh.aboo.board.model.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kh.aboo.board.model.repository.BoardRepository;
import com.kh.aboo.board.model.service.BoardService;
import com.kh.aboo.board.model.vo.Board;

import common.code.ErrorCode;
import common.exception.ToAlertException;
import common.util.file.FileUtil;
import common.util.file.FileVo;
import common.util.paging.Paging;

@Service
public class BoardServiceImpl implements BoardService {
	
	private final BoardRepository boardRepository;
	
	public BoardServiceImpl(BoardRepository boardRepository) {
		this.boardRepository = boardRepository;
	}
	// 트랜잭션 관리 2가지
	// 1. servlet context에 관련 어노테이션 사용 등록후 @Transactional 어노테이션 달아주기.

	@Override
	public void insertBoard(Board board, List<MultipartFile> files) {
		// files넘어오면 이제 DB 저장
		// 중복막기위해 renameFileName
		FileUtil fileUtil = new FileUtil();
		
		// 게시판 글 등록
		boardRepository.insertBoard(board);
		
		try {
			
			List<FileVo> fileList = fileUtil.fileUpload(files);
			for (FileVo fileVo : fileList) {
				// 파일 등록
				boardRepository.insertFile(fileVo);
			}
		} catch (Exception e) {
			throw new ToAlertException(ErrorCode.IB01, e);
		}
		
	}

	// 페이징객체와 보드리스트 둘다 보내준다.
	@Override
	public Map<String,Object> selectBoardList(int currentPage) {
		Paging paging = Paging.builder()
				.currentPage(currentPage)
				.blockCnt(5) //페이지 버튼 숫자 개수
				.cntPerPage(10) //몇개씩 보여줄건지.
				.type("board")
				.total(boardRepository.selectContentCnt())
				.build();
				
		Map<String,Object> commMap = new HashMap<String, Object>();
		commMap.put("paging", paging);
		commMap.put("boardList", boardRepository.selectBoardList(paging));
		
		return commMap;
	}

	@Override
	public Map<String, Object> selectBoardDetail(String bdIdx) {
		Board board = boardRepository.selectBoardDetail(bdIdx);
		List<FileVo> files = boardRepository.selectFileWithBdIdx(bdIdx);

		
		Map<String, Object> commandMap = new HashMap<String, Object>();

		commandMap.put("board", board);
		commandMap.put("files", files);
		return commandMap;
	}


}
