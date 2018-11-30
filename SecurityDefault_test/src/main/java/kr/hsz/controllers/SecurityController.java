package kr.hsz.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.hsz.domain.account.Account;
import kr.hsz.domain.account.AccountService;
import kr.hsz.dto.TempUser;
import kr.hsz.security.service.SecurityService;

@Controller
public class SecurityController {

	@Autowired AccountService accountSercice;
	@Autowired SecurityService securityService;

//	@RequestMapping("/login_view")
//	public String loginView() {
//		return "login";
//	}

	// 로그인
	@RequestMapping("/login_view")
	public String login(Model model, String error, String logout, HttpServletRequest request) {
		if (logout != null) {
			model.addAttribute("logout", "You have been logged out successfully.");
		}
		return "login";
	}

	// 로그인 실패시
	@RequestMapping(value = "/loginError")
	public String loginError(Model model, String username) {
		model.addAttribute("error", "Your username and password is invalid.");
		model.addAttribute("username", username);
		return "login";
	}

	// 회원가입폼
	@GetMapping(value = "/registration")
	public String registration(Model model) {
		model.addAttribute("userForm", new TempUser());
		return "registration";
	}

	// 회원가입 처리 후 로그인
	@PostMapping(value = "/registration")
	public String registration(@ModelAttribute("tempUser") TempUser tempUser, BindingResult bindingResult, Model model, String[] roles) {
		Account account = new Account(tempUser);
		account = accountSercice.create(account);
		securityService.autologin(tempUser.getUserId(), tempUser.getUserPassword());
		return "redirect:/";
	}

	/*
	// 권한없는 페이지를 들어갔을때
	@RequestMapping("/403")
	public String access() {
		return "error/403";
	}
*/
}
