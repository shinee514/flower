package com.today.flower.storeitem;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StoreItemController {
	
	@GetMapping(value= "/admin/item/new")
	public String storeItemForm(Model model) {
		model.addAttribute("storeItemFormDto", new StoreItemFormDto());
		return "storeItemForm";
	}
}