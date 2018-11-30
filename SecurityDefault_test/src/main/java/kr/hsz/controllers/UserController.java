package kr.hsz.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/user")
@Slf4j
public class UserController {

	@RequestMapping({"/", ""})
	public String user() {
		logger.info("user ~~~~~~~~~ ");
		return "user/user";
	}
	
	
	
	
	
	
	
	
	
	
	
}
