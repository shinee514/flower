package com.today.flower.storeitem;

import org.apache.groovy.parser.antlr4.util.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.today.flower.FileService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class StoreItemImgService {

	@Value("${storeItemImgLocation}")
	private String storeItemImgLocation;
	
	private final StoreItemImgRepository storeItemImgRepository;
	
	private final FileService fileService;
	
	public void saveStoreItemImg(StoreItemImg storeItemImg, MultipartFile storeItemImgFile) throws Exception{
		String oriImgName = storeItemImgFile.getOriginalFilename();
		String imgName = "";
		String imgUrl = "";
		
		//파일업로드
		if(!StringUtils.isEmpty(oriImgName)) {
			imgName = fileService.uploadFile(storeItemImgLocation, oriImgName, storeItemImgFile.getBytes());
			imgUrl = "/images/item" + imgName;
		}
		
		//상품 이미지 정보 저장
		storeItemImg.updateItemImg(oriImgName, imgName, imgUrl);
		storeItemImgRepository.save(storeItemImg);
	}
}
