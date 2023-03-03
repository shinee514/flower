package com.today.flower.storeitem;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class StoreItemService {
	
	private final StoreItemRepository storeItemRepository;
	private final StoreItemImgService storeItemImgService;
	private final StoreItemImgRepository storeItemImgRepository;
	
	public Long saveStoreItem(StoreItemFormDto storeItemFormDto, List<MultipartFile> storeItemImgFileList) throws Exception{
		//상품등록
		StoreItem storeItem = storeItemFormDto.createItem();
		storeItemRepository.save(storeItem);
		//이미지등록
		for(int i=0; i<storeItemImgFileList.size(); i++) {
			StoreItemImg storeItemImg = new StoreItemImg();
			storeItemImg.setStoreitem(storeItem);
			if(i == 0) {
				storeItemImg.setRepimgYn("Y");
			}else {
				storeItemImg.setRepimgYn("N");
			}
			storeItemImgService.saveStoreItemImg(storeItemImg, storeItemImgFileList.get(i));
		}
		return storeItem.getId();
	}

}
