package kr.hsz.controllers;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.AllArgsConstructor;
import lombok.Data;

@Controller
public class RootController {

	@RequestMapping("/")
	public String index(Locale locale, Model model) {
		
		Students students = new Students(1, "홍길동");
		model.addAttribute("students", students);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
			
		String formattedDate = dateFormat.format(date);
			
		model.addAttribute("serverTime", formattedDate );
		
		return "index";
	}
	
	@Data
	@AllArgsConstructor
	private class Students {
		
		private Integer stdId;
		
		private String stdName;
		
	}
	
	
	
	
	
	
	
	
	
	
}
