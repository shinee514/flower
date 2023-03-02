package com.today.flower.store;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequestMapping("/store")
@RequiredArgsConstructor
@Controller
public class StoreController {
	
	private final StoreRepository storeRepository;
	private final StoreService storeService;
	
	
	@GetMapping("/regi")
    public String list(Model model) {
        List<Store> storeList = this.storeRepository.findAll();
        model.addAttribute("storeList", storeList);
        return "store_regi";
    }
	
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
}