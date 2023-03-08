package com.today.flower.store;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequestMapping("/store")
@RequiredArgsConstructor
@Controller
public class StoreController {
	
	private final StoreService storeService;
	
	@GetMapping("/regi")
    public String list(Model model) {
        List<Store> storeList = this.storeService.getList();
        model.addAttribute("storeList", storeList);
        return "store_regi";
    }
	/*
	@GetMapping("/create")
	public String storeCreate(StoreForm storeForm) {
		return "store_create";
	}
	
	 @PostMapping("/create")
	    public String storeCreate(@Valid StoreForm storeForm, BindingResult bindingResult) {
		 if (bindingResult.hasErrors()) {
	            return "store_create";
	        }
		 	this.storeService.create(storeForm.getStoreName(), storeForm.getStoreAddr(), storeForm.getOpenTime(), storeForm.getMinAmount(), storeForm.getDeliveryTips(), storeForm.getFreeDelivery(), storeForm.getPickupTime());
	        return "redirect:/store/regi";
	    }
	 */
	 
	 @GetMapping(value = "/create")
	    public String storeForm(Model model){
	        model.addAttribute("storeForm", new StoreForm());
	        return "store_create";
	    }
		
		@PostMapping(value= "/create")
		public String storeNew(@Valid StoreForm storeForm, BindingResult bindingResult, Model model, @RequestParam("storeImgFile") List<MultipartFile> storeImgFileList) {
			
			if(bindingResult.hasErrors()) {
				return "store_create";
			}
			
			if(storeImgFileList.get(0).isEmpty() && storeForm.getStoreId() == null) {
				model.addAttribute("errorMessage", "첫번째 상품 이미지는 필수 입력 값입니다.");
				return "store_create";
			}
			
			try {
				storeService.saveStore(storeForm, storeImgFileList);
			}catch(Exception e) {
				model.addAttribute("errorMessage", "상품 등록 중 에러가 발생하였습니다.");
				return "store_create";
			}
			
			return "redirect:/store/regi";
		}
}