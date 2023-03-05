package com.today.flower;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@RequestMapping("/flower")
@RequiredArgsConstructor
@Controller
public class FlowerController {
	
	@GetMapping("/ci")
	public String ci() {
		return "ci/ci";
	}
	@GetMapping("/home")
	public String home() {
		return "home/home1";
	}
	
	@GetMapping("/")
	public String root() {
		return "redirect:/flower/main";
	}
}