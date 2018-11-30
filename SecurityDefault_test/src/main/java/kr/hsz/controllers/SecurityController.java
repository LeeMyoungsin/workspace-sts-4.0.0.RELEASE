package kr.hsz.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SecurityController {

	@RequestMapping("/login_view")
	public String loginView() {
		return "login";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
