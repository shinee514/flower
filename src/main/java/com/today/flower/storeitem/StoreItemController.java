package com.today.flower.storeitem;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.today.flower.store.StoreFormDto;
import com.today.flower.store.StoreService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class StoreItemController {
	
	private final StoreItemService storeItemService;
	private final StoreService storeService;
	/*
	@GetMapping(value= "/admin/item/new")
	public String storeItemForm(Model model) {
		model.addAttribute("storeItemFormDto", new StoreItemFormDto());
		return "storeItemForm";
	}
	*/
	@GetMapping(value= "/store/item/{storeId}")
	public String storeItemForm(Model model, @PathVariable("storeId")Integer storeId) {
		StoreFormDto storeFormDto = storeService.getStoreDtl(storeId);
		model.addAttribute("storeItemFormDto", new StoreItemFormDto());
		model.addAttribute("store", storeFormDto);
		return "storeItemForm";
	}
	
	@PostMapping(value= "/store/item/{storeId}")
	public String itemNew(@Valid StoreItemFormDto storeItemFormDto, BindingResult bindingResult, Model model, @RequestParam("storeItemImgFile") List<MultipartFile> storeItemImgFileList) {
		
		if(bindingResult.hasErrors()) {
			return "storeItemForm";
		}
		
		if(storeItemImgFileList.get(0).isEmpty() && storeItemFormDto.getId() == null) {
			model.addAttribute("errorMessage", "첫번째 상품 이미지는 필수 입력 값입니다.");
			return "storeItemForm";
		}
		
		try {
			storeItemService.saveStoreItem(storeItemFormDto, storeItemImgFileList);
		}catch(Exception e) {
			model.addAttribute("errorMessage", "상품 등록 중 에러가 발생하였습니다.");
			return "storeItemForm";
		}
		
		return "redirect:/";
	}
	
	@GetMapping(value = "/admin/item/{storeItemId}")
	public String itemDtl(@PathVariable("storeItemId") Long storeItemId, Model model) {
		try {
			StoreItemFormDto storeItemFormDto = storeItemService.getItemDtl(storeItemId);
			model.addAttribute("storeItemFormDto", storeItemFormDto);
		}catch(EntityNotFoundException e) {
			model.addAttribute("errorMessage", "존재하지 않는 상품 입니다.");
			model.addAttribute("storeItemFormDto", new StoreItemFormDto());
			return "storeItemForm";
		}
		return "storeItemForm";
	}
	
	@PostMapping(value = "/admin/item/{storeItemId}")
	public String itemUpdate(@Valid StoreItemFormDto storeItemFormDto, BindingResult bindingResult, @RequestParam("storeItemImgFile") List<MultipartFile> storeItemImgFileList, Model model) {
		
		if(bindingResult.hasErrors()) {
			return "storeItemForm";
		}
		if(storeItemImgFileList.get(0).isEmpty() && storeItemFormDto.getId() == null) {
			model.addAttribute("errorMessage", "첫번째 상품 이미지는 필수 입력 값 입니다.");
			return "storeItemForm";
		}
		try {
			storeItemService.updateItem(storeItemFormDto, storeItemImgFileList);
		}catch (Exception e) {
			model.addAttribute("errorMessage", "상품 수정 중 에러가 발생하였습니다.");
			return "storeItemForm";
		}
		return "redirect:/";
	}
}