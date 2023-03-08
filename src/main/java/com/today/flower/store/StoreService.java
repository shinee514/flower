package com.today.flower.store;


import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.today.flower.storeitem.StoreItem;
import com.today.flower.storeitem.StoreItemFormDto;
import com.today.flower.storeitem.StoreItemImg;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class StoreService {
	
	private final StoreRepository storeRepository;
	private final StoreImgService storeImgService;
	
	public List<Store> getList(){
		return this.storeRepository.findAll();
	}
	
	public void create(String storeName, String storeAddr, String openTime, String minAmount, String deliveryTips, String freeDelivery, String pickupTime) {
        Store s = new Store();
        s.setStoreName(storeName);
        s.setStoreAddr(storeAddr);
        s.setOpenTime(openTime);
        s.setMinAmount(minAmount);
        s.setDeliveryTips(deliveryTips);
        s.setFreeDelivery(freeDelivery);
        s.setPickupTime(pickupTime);
        this.storeRepository.save(s);
    }
	
	@Transactional(readOnly = true)
    public StoreFormDto getStoreDtl(Integer storeId){
        Store store = storeRepository.findById(storeId)
                .orElseThrow(EntityNotFoundException::new);
        StoreFormDto storeFormDto = StoreFormDto.of(store);
        return storeFormDto;
    }
	
	public Integer saveStore(StoreForm storeForm, List<MultipartFile> storeImgFileList) throws Exception{
		//상품등록
		Store store = storeForm.createStore();
		storeRepository.save(store);
		//이미지등록
		for(int i=0; i<storeImgFileList.size(); i++) {
			StoreImg storeImg = new StoreImg();
			storeImg.setStore(store);
			if(i == 0) {
				storeImg.setRepimgYn("Y");
			}else {
				storeImg.setRepimgYn("N");
			}
			storeImgService.saveStoreImg(storeImg, storeImgFileList.get(i));
		}
		return store.getStoreId();
	}
}