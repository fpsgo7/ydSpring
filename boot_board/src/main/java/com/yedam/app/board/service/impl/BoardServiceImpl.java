package com.yedam.app.board.service.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.yedam.app.board.mapper.BoardMapper;
import com.yedam.app.board.service.BoardService;
import com.yedam.app.board.service.BoardVO;

import lombok.RequiredArgsConstructor;

@Service// AOP => @Transactional
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{

	private final BoardMapper boardMapper;
	
	@Override
	public List<BoardVO> boardList() {
		return boardMapper.selectBoardAll();
	}

	@Override
	public BoardVO boardInfo(BoardVO boardVO) {
		return boardMapper.selectBoardInfo(boardVO);
	}

	@Override
	public int insertBoard(BoardVO boardVO) {
		// selectKey에의해 boardVO 의 bno에 자동으로 값이 채워진다.(테스트에서 확인할 수 있다.)
		int result = boardMapper.insertBoardInfo(boardVO);
		return result == 1 ? boardVO.getBno() : -1;
	}

	@Override
	public Map<String, Object> updateBoard(BoardVO boardVO) {
		Map<String, Object> map = new HashMap<>();
		boolean isSuccessed 
			= boardMapper.updateBoardInfo(boardVO) == 1 
				? true : false;
		String updateDate = getUpdateDate();
		map.put("date",updateDate);
		map.put("result", isSuccessed);
		map.put("target", boardVO);
		return map;
	}
	
	/**
	 * Date 클래스는 포멧이 불편하여 
	 * 다른 포멧을 사용하기위해 해당 메서드가 추가되었다.***
	 */
	private String getUpdateDate() {
		LocalDate today = LocalDate.now();
		DateTimeFormatter dateFormat 
			= DateTimeFormatter.ofPattern("yyyy/MM/dd");
		String updateDate = today.format(dateFormat);
		return updateDate;
	}

	@Override
	public int deleteBoard(int bno) {
		return boardMapper.deleteBoardInfo(bno);
	}

}
