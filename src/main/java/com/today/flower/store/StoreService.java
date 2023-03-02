package com.today.flower.store;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class StoreService {
	
	private final StoreRepository storeRepository;
	
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
}