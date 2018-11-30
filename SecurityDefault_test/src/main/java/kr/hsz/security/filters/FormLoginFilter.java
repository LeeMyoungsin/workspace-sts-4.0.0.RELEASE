package kr.hsz.security.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.hsz.dto.FormLoginDto;
import kr.hsz.security.jwt.JwtDecoder;
import kr.hsz.security.tokens.PreAuthorizationToken;

public class FormLoginFilter extends AbstractAuthenticationProcessingFilter {

	private static final Logger logger = LoggerFactory.getLogger(FormLoginFilter.class);
	
    private AuthenticationSuccessHandler authenticationSuccessHandler;
    private AuthenticationFailureHandler authenticationFailureHandler;

    protected FormLoginFilter(String defaultFilterProcessesUrl) {
        super(defaultFilterProcessesUrl);
    }
    
    public FormLoginFilter(String defaultUrl, AuthenticationSuccessHandler successHandler, AuthenticationFailureHandler failureHandler) {
        super(defaultUrl);

        this.authenticationSuccessHandler = successHandler;
        this.authenticationFailureHandler = failureHandler;
        
        logger.info("");
        logger.info(">>>>> FormLoginFilter <<<<<");
        logger.info("");
        
    }
    
    
    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) throws AuthenticationException, IOException, ServletException {
    	
    	logger.info(">>>>> FormLoginFilter 000000000");
    	
        FormLoginDto dto = new ObjectMapper().readValue(req.getReader(), FormLoginDto.class);
        
        logger.info(">>>>> FormLoginFilter 000000001");
        
        PreAuthorizationToken token = new PreAuthorizationToken(dto);

        logger.info(">>>>> FormLoginFilter 000000002");
        
        return super.getAuthenticationManager().authenticate(token);
    }

    
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        this.authenticationSuccessHandler.onAuthenticationSuccess(request, response, authResult);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        AuthenticationFailureHandler handler = (req, res, exception) -> {
            Logger log = LoggerFactory.getLogger("authentication_failure");

            log.error(exception.getMessage());
        };

        handler.onAuthenticationFailure(request, response, failed);
    }
}
