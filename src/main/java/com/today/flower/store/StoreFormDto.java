package com.today.flower.store;

import org.modelmapper.ModelMapper;

import com.today.flower.storeitem.StoreItem;
import com.today.flower.storeitem.StoreItemFormDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StoreFormDto {
	
	 	private Integer storeId;
		
		private String storeName;
		
		private String storeAddr;
		
		 private static ModelMapper modelMapper = new ModelMapper();


		    public static StoreFormDto of(Store store){
		        return modelMapper.map(store,StoreFormDto.class);
		    }
}