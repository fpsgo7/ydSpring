package com.yedam.app.upload.web;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
@Controller
public class UploadController {
	// ${} 메모리에서의 변수값을 가져온다.
	// 환경변수 및 application.properties 파일의 변수 값을 Read
	@Value("${file.upload.path}")
	private String uploadPath;
	
	/**
	 * void 타입은 파일명과 경로가 같을 때 사용한다.
	 */
	@GetMapping("formUpload")
	public void formUploadPage() {
		System.out.println(uploadPath);
	}
	// classpath:/templates/formUpload.html
	
	@PostMapping("uploadForm")
	public String formUploadFile
			// 매개변수 명 과 input 태그의 name 값이 일치해야한다.
			(@RequestPart MultipartFile[] files) {
		for (MultipartFile file : files) {
			System.out.println(file.getOriginalFilename());// 파일의 전체 이름 추출
			System.out.println(file.getContentType()); // 파일 확상자 추출
			System.out.println(file.getSize()); // 파일의 사이즈
			
			// 파일 업로드하기
			String fileName = file.getOriginalFilename();
			// File.separator <-- 운영체제별로 구분을 위해 사용하나 우린
			// java의 Path를 사용하니 꼭 필요하지 않다.
			//String saveName = uploadPath + File.separator + fileName;
			String saveName = uploadPath + fileName;
			
			System.out.println("saveName : " + saveName);
			Path savePath = Paths.get(saveName); // 자바를 위한 path 구하기(안정성)
			
			try {
				file.transferTo(savePath);// 해당 경로에 저장하기
			} catch (IllegalStateException e) { // 경로가 문제일경우
				e.printStackTrace();
			} catch (IOException e) {// 실행중 문제가 발생할경우
				e.printStackTrace();
			}
			
		}
		
		return "formUpload";// 해당 경로로 요청을 보낸다.
	}
	
	/* 교수님 방식 (실제 데이터 저장하는 방식) */
	/**
	 * 업로드 하기위한 페이지
	 */
	@GetMapping("upload")
	public void uploadPage() {}
	
	/**
	 * 파일을 올리는 함수이다.
	 */
	@PostMapping("/uploadsAjax")
	@ResponseBody
	public List<String> uploadFile
	(@RequestPart MultipartFile[] uploadFiles) {
	    
		List<String> imageList = new ArrayList<>();

	    for(MultipartFile uploadFile : uploadFiles){
	    	// 이미지 파일이 아니면은 처리하지 않는다.(콘텐츠 타입으로 확인)
		    if(uploadFile.getContentType().startsWith("image") == false){
		    	System.err.println("this file is not image type");
		    	return null;
		    }
		  
	        String fileName = uploadFile.getOriginalFilename();
	        System.out.println("fileName : " + fileName);
	        // 충돌방지를 위해 날짜 폴더와 UUID를 통한 고유식별자를 활용한다.
	        //날짜 폴더 생성
	        String folderPath = makeFolder();
	        //UUID 고유 식별자를통해 중복 방지 
	        String uuid = UUID.randomUUID().toString();
	        //저장할 파일 이름 중간에 "_"를 이용하여 구분
	        String uploadFileName = folderPath +File.separator + uuid + "_" + fileName;
	        String saveName = uploadPath + File.separator + uploadFileName;
	        //Paths.get() 메서드는 특정 경로의 파일 정보를 가져옵니다.(경로 정의하기)
	        Path savePath = Paths.get(saveName);
	        System.out.println("path : " + saveName);
	        try{
	        	//uploadFile에 파일을 업로드 하는 메서드 transferTo(file)
	        	uploadFile.transferTo(savePath);  
	        } catch (IOException e) {
	             e.printStackTrace();             
	        }
	        // DB에 해당 경로 저장
	        // 1) 사용자가 업로드할 때 사용한 파일명
	        // 2) 실제 서버에 업로드할 때 사용한 경로
	        imageList.add(setImagePath(uploadFileName));
	    }
	    return imageList;
	}

	/**
	 * 현재 날짜 값을 토대로 폴더를 만들어주는 메서드이다.
	 * @return 폴더 경로 내용
	 */
	private String makeFolder() {
		String str = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
		
		// LocalDate를 문자열로 포멧
		String folderPath = str.replace("/", File.separator);
		File uploadPathFoler = new File(uploadPath, folderPath);
		
		// File newFile= new File(dir,"파일명");
		if (uploadPathFoler.exists() == false) {
			uploadPathFoler.mkdirs();
			// 만약 uploadPathFolder가 존재하지않는다면 makeDirectory하라는 의미입니다.
			// mkdir(): 디렉토리에 상위 디렉토리가 존재하지 않을경우에는 생성이 불가능한 함수
			// mkdirs(): 디렉토리의 상위 디렉토리가 존재하지 않을 경우에는 상위 디렉토리까지 모두 생성하는 함수
		}
		return folderPath;
	}
	
	/**
	 * DB 에 경로를 저장하기위해 사용
	 */
	private String setImagePath(String uploadFileName) {
		return uploadFileName.replace(File.separator, "/");
	}
}
