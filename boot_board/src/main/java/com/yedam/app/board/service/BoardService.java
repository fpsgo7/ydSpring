package com.yedam.app.board.service;

import java.util.List;
import java.util.Map;

public interface BoardService {
	// 전체 조회
	public List<BoardVO> boardList();
	// 단건 조회
	public BoardVO boardInfo(BoardVO boardVO);
	// 등록
	/**
	 * 게시판 글의 번호가 반환된다.
	 */
	public int insertBoard(BoardVO boardVO);
	// 수정
	/**
	 * 결과 성공 유무와
	 * boardVO 객체가 반환된다.
	 */
	public Map<String, Object> updateBoard(BoardVO boardVO);
	// 삭제
	public int deleteBoard(int bno);
}
