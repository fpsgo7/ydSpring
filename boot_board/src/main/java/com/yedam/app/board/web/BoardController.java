package com.yedam.app.board.web;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.app.board.service.BoardService;
import com.yedam.app.board.service.BoardVO;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class BoardController {
	private final BoardService boardService;
	
	// 전체조회 : URI - boardList / RETURN - board/boardList
	@GetMapping("boardList")
	public String boardList(Model model) {
		List<BoardVO> list = boardService.boardList();
		model.addAttribute("boards", list);
		return "board/boardList";
	}
	
	// 단건조회 : URI - boardInfo / PARAMETER - BoardVO(QueryString)
	// 매배견수 : 커멘드 객체, Model 객체
	//          RETURN - board/boardInfo
	// QueryString 값 매개변수로 받기
	// 1) 객체 : 커멘드 객체
	// 2) 단일값 : @RequestParam  
	@GetMapping("boardInfo")
	public String boardInfo(BoardVO boardVO,Model model) {
		BoardVO findVO = boardService.boardInfo(boardVO);
		model.addAttribute("board", findVO);
		return "board/boardInfo";
	}
	
	// 등록 - 페이지 : URI - boardInsert / RETURN - board/boardInsert
	@GetMapping("boardInsert")
	public String boardInsertForm() {
		return "board/boardInsert";
	}
	
	// 등록 - 처리 : URI - boardInsert / PARAMETER - BoardVO(QueryString)
	//             RETURN - 단건조회 다시 호출
	@PostMapping("boardInsert")
	public String boardInsertProcess(BoardVO boardVO) { // <from/> 활용한 submit 사용
		int bid = boardService.insertBoard(boardVO);
		return "redirect:boardInfo?bno=" + bid;
	}
	
	// 수정 - 페이지 : URI - boardUpdate / PARAMETER - BoardVO(QueryString)
	//               RETURN - board/boardUpdate
	// 수정 페이지는 단건조회를 해야하기에 단건 조회 컨트롤러와 유사하다.
	@GetMapping("boardUpdate")
	public String boardUpdateForm(BoardVO boardVO, Model model) {
		BoardVO findVO = boardService.boardInfo(boardVO);
		model.addAttribute("board",findVO);
		return "board/boardUpdate";
	}
	
	// 수정 - 처리 : URI - boardUpdate / PARAMETER - BoardVO(JSON) <-@RequestBody 필요
	//             RETURN - 수정결과 데이터(Map) <-- AJAX 방식 사용 @ResponseBody 필요
	@PostMapping("boardUpdate")
	@ResponseBody
	public Map<String, Object> boardUpdateProcess(
			@RequestBody BoardVO boardVO
			){
		return boardService.updateBoard(boardVO);
	}
	
	// 삭제 - 처리 : URI - boardDelete / PARAMETER - Integer
	//             RETURN - 전체조회 다시 호출
	// 매개변수 : 단일변수 처리이며 QueryString 이기에 @RequestParam 사용한다.
	@GetMapping("boardDelete")
	public String boardDeleteProcess(@RequestParam Integer bno) {
		int result = boardService.deleteBoard(bno);
		return "redirect:boardList";// redirect는 경로를 다시 요청하는 것이다!
	}
}
