package com.today.flower.store;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Integer> {
	
	List<Store> findByStoreId(Integer storeId);

}