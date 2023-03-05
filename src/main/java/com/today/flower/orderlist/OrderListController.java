package com.today.flower.orderlist;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class OrderListController {
	
	@GetMapping("/Faq")
	@ResponseBody
	public String index() {
		return "index";
	}

}
