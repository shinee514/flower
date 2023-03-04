package com.today.flower.storeitem;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class StoreItemController {
	
	private final StoreItemService storeItemService;
	
	@GetMapping(value= "/admin/item/new")
	public String storeItemForm(Model model) {
		model.addAttribute("storeItemFormDto", new StoreItemFormDto());
		return "storeItemForm";
	}
	
	@PostMapping(value= "/admin/item/new")
	public String itemNew(@Valid StoreItemFormDto storeItemFormDto, BindingResult bindingResult, Model model, @RequestParam("storeItemImgFile") List<MultipartFile> storeItemImgFileList) {
		
		if(bindingResult.hasErrors()) {
			return "storeItem/storeItemForm";
		}
		
		if(storeItemImgFileList.get(0).isEmpty() && storeItemFormDto.getId() == null) {
			model.addAttribute("errorMessage", "첫번째 상품 이미지는 필수 입력 값입니다.");
			return "storeItem/storeItemForm";
		}
		
		try {
			storeItemService.saveStoreItem(storeItemFormDto, storeItemImgFileList);
		}catch(Exception e) {
			model.addAttribute("errorMessage", "상품 등록 중 에러가 발생하였습니다.");
			return "storeItem/storeItemForm";
		}
		
		return "redirect:/";
	}
}