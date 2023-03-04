package com.today.flower.order;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class OrderController {
	
	@GetMapping("/Faq")
	@ResponseBody
	public String index() {
		return "index";
	}

}
