package com.yedam.app;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
	
	//@Test
	// builder을 제거해서 이제 몼슨다.
//	@DisplayName("전체 내용 등록하기")
//	public void insertBoard1() {
//		BoardVO boardVO = BoardVO.builder()
//				.title("title")
//				.contents("contents")
//				.writer("writer")
//				.regdate(new Date())
//				.image("image")
//				.build();
//		
//		int result = boardMapper.insertBoardInfo(boardVO);
//		assertEquals(result,1);
//	}
	
	//@Test 
	@DisplayName("전체 조회하기")
	public void selectAll1() {
		List<BoardVO> list = boardMapper.selectBoardAll();
		System.out.println(list.get(0));
	}
	
	
}
