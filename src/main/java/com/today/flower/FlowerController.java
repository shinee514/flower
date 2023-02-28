package com.today.flower;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FlowerController {
	@GetMapping("/flower")
	@ResponseBody
	public String hello() {
		return "today`s flower";
	}
}