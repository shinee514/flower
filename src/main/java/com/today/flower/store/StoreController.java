package com.today.flower.store;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.EntityNotFoundException;
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
	        model.addAttribute("storeFormDto", new StoreFormDto());
	        return "store_create";
	    }
		
		@PostMapping(value= "/create")
		public String storeNew(@Valid StoreFormDto storeFormDto, BindingResult bindingResult, Model model, @RequestParam("storeImgFile") List<MultipartFile> storeImgFileList) {
			
			if(bindingResult.hasErrors()) {
				return "store_create";
			}
			
			if(storeImgFileList.get(0).isEmpty() && storeFormDto.getStoreId() == null) {
				model.addAttribute("errorMessage", "????????? ?????? ???????????? ?????? ?????? ????????????.");
				return "store_create";
			}
			
			try {
				storeService.saveStore(storeFormDto, storeImgFileList);
			}catch(Exception e) {
				model.addAttribute("errorMessage", "?????? ?????? ??? ????????? ?????????????????????.");
				return "store_create";
			}
			
			return "redirect:/store/regi";
		}
		
		@GetMapping(value = "/create/{storeId}")
		public String storeDtl(@PathVariable("storeId")Integer storeId, Model model) {
			try {
				StoreFormDto storeFormDto = storeService.getStoreDtl(storeId);
				model.addAttribute("storeFormDto", storeFormDto);
			}catch(EntityNotFoundException e) {
				model.addAttribute("errorMessage", "???????????? ?????? ?????? ?????????.");
				model.addAttribute("storeFormDto", new StoreFormDto());
				return "store_create";
			}
			return "store_create";
		}
		
		@PostMapping(value = "/create/{storeId}")
		public String storeUpdate(@Valid StoreFormDto storeFormDto, BindingResult bindingResult, @RequestParam("storeImgFile") List<MultipartFile> storeImgFileList, Model model) {
			if(bindingResult.hasErrors()) {
				return "store_create";
			}
			if(storeImgFileList.get(0).isEmpty() && storeFormDto.getStoreId() == null) {
				model.addAttribute("errorMessage", "????????? ?????? ???????????? ?????? ?????? ??? ?????????.");
				return "store_create";
			}
			
			try {
				storeService.updateStore(storeFormDto, storeImgFileList);
			}catch(Exception e) {
				model.addAttribute("errorMessage", "?????? ?????? ??? ????????? ?????????????????????.");
				return "store_create";
			}
			return "redirect:/store/regi";
		}
		
		
		@GetMapping(value="/{storeId}")
		public String storeDtl(Model model, @PathVariable("storeId") Integer storeId) {
			StoreFormDto storeFormDto = storeService.getStoreDtl(storeId);
			model.addAttribute("store", storeFormDto);
			return "storeDtl";
		}
		
		@GetMapping(value = {"/admin/stores", "/admin/stores/{page}"})
		public String storemanage(StoreSearchDto storeSearchDto, @PathVariable("page") Optional<Integer> page, Model model) {
			Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 3);
			
			Page<Store> stores=
					storeService.getStorePage(storeSearchDto, pageable);
			model.addAttribute("stores", stores);
			model.addAttribute("storeSearchDto", storeSearchDto);
			model.addAttribute("maxPage",5);
			return "storeMng";
		}
		
		
}