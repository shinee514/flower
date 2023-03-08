package com.today.flower.store;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StoreFormDto {
	
	 	private Integer storeId;
		
		private String storeName;
		
		private String storeAddr;
		
		private List<StoreImgDto> storeImgDtoList = new ArrayList<>();
		
		private List<Long> storeImgIds = new ArrayList<>();
		
		private static ModelMapper modelMapper = new ModelMapper();
		
		public Store createStore(){
	        return modelMapper.map(this, Store.class);
	    }

		public static StoreFormDto of(Store store){
		        return modelMapper.map(store,StoreFormDto.class);
		    }
}