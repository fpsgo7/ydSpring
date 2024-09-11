package com.yedam.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.yedam.app.board.mapper.BoardMapper;
import com.yedam.app.board.service.BoardVO;

@SpringBootTest
public class BoardMapperTest {
	@Autowired
	private BoardMapper boardMapper;
	
	@Test
	@DisplayName("전체 내용 등록하기 와 selectKey 기능 확인하기")
	public void insertBoard1() {
		BoardVO boardVO = new BoardVO();
		boardVO.setTitle("1");
		boardVO.setContents("2");
		boardVO.setRegdate(new Date());
		boardVO.setWriter("3");
		// bno필드는 채우지 않았다.
		System.out.println(boardVO.getBno());// null 예상
		int result = boardMapper.insertBoardInfo(boardVO);
		assertEquals(result,1);
		System.out.println(boardVO.getBno());// 값이 채워짐
		assertNotNull(boardVO.getBno());// selectKey로 값을 가져온다.
	}
	
	//@Test 
	@DisplayName("전체 조회하기")
	public void selectAll1() {
		List<BoardVO> list = boardMapper.selectBoardAll();
		System.out.println(list.get(0));
	}
	
	
}
