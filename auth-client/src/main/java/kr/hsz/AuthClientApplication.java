package kr.hsz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import kr.hsz.filters.SimpleFilter;

@SpringBootApplication
public class AuthClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthClientApplication.class, args);
	}
	
    @Bean
    public SimpleFilter simpleFilter() {
      return new SimpleFilter();
    } 
}
