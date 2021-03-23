package com.kh.aboo.board.model.repository;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.kh.aboo.board.model.vo.Board;

import common.util.file.FileVo;
import common.util.paging.Paging;

@Mapper
public interface BoardRepository {
	// 게시글 업로드
	@Insert("insert into tb_board(bd_idx, user_id, title, content) values('b'||sc_board_idx.nextval, #{userId}, #{title}, #{content})")
	int insertBoard(Board board);
	
	
	// 파일 업로드
	// 게시글 작성할 때 파일 업로드
	int insertFile(FileVo file);
	
	// 파일 페이징
	List<Board> selectBoardList(Paging paging);
	
	@Select("select count(*) from tb_board")
	int selectContentCnt();
	
	@Select("select * from tb_board where bd_idx = #{bdIdx}")
	Board selectBoardDetail(String bdIdx);

	@Select("select * from tb_file where type_idx = #{bdIdx}")
	List<FileVo> selectFileWithBdIdx(String bdIdx);
}
