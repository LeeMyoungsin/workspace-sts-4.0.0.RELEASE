package kr.hsz.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.hsz.domain.account.AccountService;
import kr.hsz.security.service.SecurityService;

@Controller
public class ErrorController {

	@Autowired AccountService accountSercice;
	@Autowired SecurityService securityService;

	// 권한없는 페이지를 들어갔을때
	@RequestMapping("/403")
	public String access() {
		return "error/403";
	}

}
