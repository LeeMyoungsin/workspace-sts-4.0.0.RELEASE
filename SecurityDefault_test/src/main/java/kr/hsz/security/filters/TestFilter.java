package kr.hsz.security.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.hsz.dto.FormLoginDto;
import kr.hsz.security.tokens.PreAuthorizationToken;

public class TestFilter extends AbstractAuthenticationProcessingFilter {

	protected TestFilter(String defaultFilterProcessesUrl) {
		super(defaultFilterProcessesUrl);
		
		
		
		
		
		
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
			throws AuthenticationException, IOException, ServletException {
		
		FormLoginDto dto = new ObjectMapper().readValue(req.getReader(), FormLoginDto.class);
		PreAuthorizationToken token = new PreAuthorizationToken(dto);
		
		
		
		
		return super.getAuthenticationManager().authenticate(token);
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		
		
		
		
		
		
		
		
		super.successfulAuthentication(request, response, chain, authResult);
	}

	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {


		
		
		
		
		
		
		
		super.unsuccessfulAuthentication(request, response, failed);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

}
