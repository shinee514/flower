package com.today.flower.store;


import java.util.ArrayList;
import java.util.List;

import com.today.flower.storeitem.StoreItem;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@Table(name="store")
@ToString
public class Store {
	
	@Id
	@Column(name = "store_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	
	@Column(unique = true)
	private String storeName;
	
	@Column(nullable = false)
	private String storeAddr;
	
	private String openTime;
	
	private String minAmount;
	
	private String deliveryTips;
	
	private String freeDelivery;
	
	private String pickupTime;
	
	@OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
	private List<StoreItem> storeItems = new ArrayList<>();
}