package com.today.flower.store;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StoreSearchDto {
	
	private String searchDateType;
	
	private String searchBy;
	
	private String searchQuery = "";
}
