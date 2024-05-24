package com.banana.customer;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.banana.lib.CommonUtil;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/customer")
public class MainController {

	@GetMapping("")
	public String viewHomePage(Model model) {
		String appName = CommonUtil.getAppName();
		
		model.addAttribute("appName", appName);
		
		return "index";
	}
}
