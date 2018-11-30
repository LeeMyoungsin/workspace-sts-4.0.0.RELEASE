package kr.hsz.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.filter.CharacterEncodingFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.hsz.security.handlers.AccessDeniedHandlerImpl;
import kr.hsz.security.handlers.LoginFailureHandlerImpl;
import kr.hsz.security.handlers.LoginSuccessHandlerImpl;
import kr.hsz.security.handlers.LogoutSuccessHandlerImpl;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired private UserDetailsService 		userDetailsService;
	@Autowired private PasswordEncoder 			passwordEncoder;
	
	@Autowired private LoginSuccessHandlerImpl 	loginSuccessHandler;
	@Autowired private LoginFailureHandlerImpl 	loginFailureHandler;
	@Autowired private LogoutSuccessHandlerImpl logoutSuccessHandler;
	@Autowired private AccessDeniedHandlerImpl 	accessDeniedHandler;
	
	
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	@Bean
    public ObjectMapper getObjectMapper() {
        return new ObjectMapper();
    }
	
	@Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
	}
    
	@Override
	public void configure(WebSecurity web) throws Exception {
		/* Security 제외 패턴 */
		web.ignoring().antMatchers("/css/**", "/script/**", "image/**", "/fonts/**", "lib/**", "/favicon.ico", "/webjars/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		CharacterEncodingFilter filter = new CharacterEncodingFilter();
		filter.setEncoding("UTF-8");
		filter.setForceEncoding(true);
		http.addFilterBefore(filter,CsrfFilter.class);
		
		// h2 console 사용을 위한 설정 시작 
		http
			.csrf()
				.ignoringAntMatchers("/h2console/**");
		
		http
			.headers()
				.frameOptions()
				.sameOrigin()	//.disable()
		;
		// h2 console 사용을 위한 설정 끝
		
		http
			.authorizeRequests()
				.antMatchers("/", "/join/**", "/resources/**","/loginError","/registration","/h2console/**").permitAll()	// 해당 url을 허용한다.
		        .antMatchers("/admin/**").hasAnyRole("ADMIN")		// admin 폴더에 경우 admin 권한이 있는 사용자에게만 허용
		        .antMatchers("/user/**").hasAnyRole("USER")			// user 폴더에 경우 user 권한이 있는 사용자에게만 허용
		        .antMatchers("/super/**").hasAuthority("ROLE_SUPER")	// user 폴더에 경우 user 권한이 있는 사용자에게만 허용
				.anyRequest()
				.authenticated()
				.and()
			.formLogin()
				.permitAll()
				.defaultSuccessUrl("/")
				.loginPage("/login_view")
				.loginProcessingUrl("/login")		//<form action="/mylogin" method="post"></fomr>
				.usernameParameter("username")
				.passwordParameter("password")
				.successHandler(loginSuccessHandler)
				.failureHandler(loginFailureHandler)
				.and()
			.logout()
				.logoutUrl("/logout")
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))	//GET 방식 사용 가능 하게함.
				.invalidateHttpSession(true)
				.deleteCookies("JSESSIONID","remember-me")
				.deleteCookies("token")
				.deleteCookies("role")
				.deleteCookies("username")
				.logoutSuccessUrl("/")
				.logoutSuccessHandler(logoutSuccessHandler)
				.and()
			.rememberMe()
				.key("myAppKey")
				.rememberMeParameter("remember-me")
				.tokenValiditySeconds(43200)//12시간 설정
				.and()
			.exceptionHandling()
				.accessDeniedHandler(accessDeniedHandler)
				.accessDeniedPage("/403")						//권한이 없을경우 해당 url로 이동
				.and()	
			.sessionManagement()
				.maximumSessions(1)
				.expiredUrl("/login_duplicate")
				.and()
				
		;
		



	}

	
	
	
	
	
	
	
	
	
	
	
	
	
}
