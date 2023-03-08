package com.today.flower.store;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StoreFormDto {
	
	 	private Integer storeId;
		
		private String storeName;
		
		private String storeAddr;
		
	    private String openTime;
	    
	    private String minAmount;
	    
	    private String deliveryTips;
	    
	    private String freeDelivery;
	    
	    private String pickupTime;
		
		public List<StoreImgDto> storeImgDtoList = new ArrayList<>();
		
		public List<Long> storeImgIds = new ArrayList<>();
		
		public static ModelMapper modelMapper = new ModelMapper();
		
		public Store createStore(){
	        return modelMapper.map(this, Store.class);
	    }

		public static StoreFormDto of(Store store){
		        return modelMapper.map(store,StoreFormDto.class);
		    }
}