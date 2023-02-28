package com.today.flower;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@RequestMapping("/flower")
@RequiredArgsConstructor
@Controller
public class FlowerController {
	
	@GetMapping("/main")
	public String hello() {
		return "main";
	}
}