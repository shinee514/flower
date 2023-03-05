package com.today.flower.storeitem;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreItemRepository extends JpaRepository<StoreItem, Long> {
	
	List<StoreItem> findById(String storeItemId);

}
