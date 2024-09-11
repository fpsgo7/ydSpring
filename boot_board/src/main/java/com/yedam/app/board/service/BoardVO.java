package com.yedam.app.board.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class BoardVO {
	private Integer bno;	// 번호
	private String title;	// 제목
	private String contents;// 내용
	private String writer;	// 작성자
	// @DateTimeFormat(pattern="yyy-MM-dd") // 해당 어노테이션이 있다면 date 타입(0000-00-00 포맷)으로 값을 받을 수 있다.
	private Date regdate;	// 작성일
	private Date updatedate;// 수정일
	private String image;	// 첨부 이미지
}
