package com.example.mybatis.handler;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.tika.Tika;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.example.mybatis.domain.FileVO;

import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;

@Component
@Slf4j
public class FileHandler {
	
	private final String UP_DIR = "D:\\HMS\\myProject\\java\\fileUpload\\";
	
	public List<FileVO> uploadFiles(MultipartFile[] files) {
		List<FileVO> flist = new ArrayList<>();
		
		LocalDate date = LocalDate.now();
		String today = date.toString();
		today = today.replace("-", File.separator);
		
		File folders = new File(UP_DIR, today);
		// => D:\\HMS\\myProject\\java\\fileUpload\\2024\\01\\29
		
		// 실제 폴더를 생성하는 명령어 : mkdir(한개의 폴더 생성) & mkdirs(여러 폴더를 한번에 생성)
		if(!folders.exists()) { // 폴더가 이미 존재하는지 확인
			folders.mkdirs();
		}
		
		// FileVO 생성 및 세팅
		for(MultipartFile file : files) {
			FileVO fvo = new FileVO();
			
			fvo.setSaveDir(today);
			fvo.setFileSize(file.getSize());
			
			String originalFileName = file.getOriginalFilename();
			String onlyFileName = originalFileName.substring(
					originalFileName.lastIndexOf(File.separator)+1);
			fvo.setFileName(onlyFileName);
			
			UUID uuid = UUID.randomUUID();
			fvo.setUuid(uuid.toString());
			// ----- fvo 기본 설정 끝 -----
			
			// 로컬디스크에 저장할 파일 생성
			String fullFileName = uuid.toString() + "_" + onlyFileName;
			
			File storeFile = new File(folders, fullFileName);
			// => D:\\HMS\\myProject\\java\\fileUpload\\2024\\01\\29\\uuid_name
			
			// 로컬디스크에 실제로 저장
			try {
				// 원본 파일
				file.transferTo(storeFile);
				
				// file-type : 이미지 파일이면 1, 아니면 0
				// => 이미지만 썸네일 가능함
				// 티카와 썸네일은 Boot에 내장되어 있지 않으므로 디펜던시에 추가해야 됨
				if(isImageFile(storeFile)) {
					fvo.setFileType(1);
					File thumnail = new File(folders, uuid.toString()+"_th_"+onlyFileName);
					
					// 썸네일 저장
					Thumbnails.of(storeFile).size(75, 75).toFile(thumnail);
				}
			} catch (Exception e) {
				e.printStackTrace();
				log.info("@@@@@ 파일 저장 오류");
			}
			
			flist.add(fvo);
			
		}
		
		return flist;
	}
	
	// 파일 핸들러 내부용 메서드
	private boolean isImageFile(File file) throws IOException{
		String mimeType = new Tika().detect(file);
		return mimeType.startsWith("image") ? true : false;
	}
}
