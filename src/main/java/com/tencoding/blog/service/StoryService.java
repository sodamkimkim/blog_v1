package com.tencoding.blog.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tencoding.blog.dto.RequestFileDto;
import com.tencoding.blog.model.Image;
import com.tencoding.blog.model.User;
import com.tencoding.blog.repository.StoryRepository;

@Service
public class StoryService {

	@Value("${file.path}") //yml에 설정한거 들고 올 때 사용하는 어노테이션
	private String uploadFolder;
	
	@Autowired
	StoryRepository storyRepository;
	
	public Page<Image> getImageList(Pageable pageable){
		return storyRepository.findAll(pageable);
	}
	@Transactional
	public void imageUpload(RequestFileDto fileDto, User user) {
		//파일 업로드 기능(해당 서버에 바이너리 파일 생성하고 성공하면 DB에 저장)
		// 중복체크
		UUID uuid = UUID.randomUUID(); // 랜덤 uuid생성
		String imageFileName = uuid + "_"+ "story";
		String newFileName =  (imageFileName.trim()).replaceAll("\\s", ""); //  \\s는 공백의미
		System.out.println("파일 명 : " + newFileName);
		
		//서버 컴퓨터의 Path를 가지고 와야 한다.
		Path imageFilePath = Paths.get(uploadFolder + newFileName);
		System.out.println("전체 파일 경로 + 파일명 :" + imageFilePath);
		
		try {
			Files.write(imageFilePath, fileDto.getFile().getBytes());
			//DB저장
			Image imageEntity = fileDto.toEntity(newFileName, user);
			storyRepository.save(imageEntity);
		} catch (IOException e) {
			e.printStackTrace();
		} //바이너리파일 들고오기.
	}
	
	
}
