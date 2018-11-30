//package kr.hsz.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Import;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//import org.thymeleaf.spring5.SpringTemplateEngine;
//import org.thymeleaf.spring5.view.ThymeleafViewResolver;
//import org.thymeleaf.templatemode.TemplateMode;
////import org.thymeleaf.templateresolver.ServletContextTemplateResolver;
//import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
//
//import kr.hsz.security.config.SecurityConfig;
//
//@Configuration
//@EnableWebMvc
//@Import(SecurityConfig.class)
//public class ThymeleafConfig implements WebMvcConfigurer {
//	
//	@Bean(name ="templateResolver")	
//    public ServletContextTemplateResolver getTemplateResolver() {
//    	
//		
//		
//		
//		
//		ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver();
//    	templateResolver.setPrefix("/WEB-INF/templates/");
//    	templateResolver.setSuffix(".html");
//    	templateResolver.setTemplateMode("XHTML");//HTML5
//    	templateResolver.setCharacterEncoding("UTF-8");
//    	templateResolver.setCacheable(false);//추후 true로 설정 바꾸어야 함.
//    	
//    	return templateResolver;
//    }
//	
//    @Bean(name ="templateEngine")	    
//    public SpringTemplateEngine getTemplateEngine() {
//    	
//    	SpringTemplateEngine templateEngine = new SpringTemplateEngine();
//    	templateEngine.setTemplateResolver(getTemplateResolver());
//    	return templateEngine;
//    }
//    
//    @Bean(name="viewResolver")
//    public ThymeleafViewResolver getViewResolver(){
//    	
//    	ThymeleafViewResolver viewResolver = new ThymeleafViewResolver(); 
//    	viewResolver.setTemplateEngine(getTemplateEngine());
//    	viewResolver.setCharacterEncoding("UTF-8");
//    	viewResolver.setOrder(1);
//    	return viewResolver;
//    }
//
//	
//	
//	
//	
//	
//	
//	
//
//}
