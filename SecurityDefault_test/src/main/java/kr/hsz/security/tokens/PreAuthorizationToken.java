package kr.hsz.security.tokens;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import kr.hsz.dto.FormLoginDto;
import kr.hsz.dto.SocialLoginDto;
import kr.hsz.enums.SocialProviders;

public class PreAuthorizationToken extends UsernamePasswordAuthenticationToken {

	private static final Logger logger = LoggerFactory.getLogger(PreAuthorizationToken.class);
	
	private static final long serialVersionUID = 1L;

	private PreAuthorizationToken(String username, String password) {
        super(username, password);
        logger.info("");
        logger.info(">>>>> PreAuthorizationToken <<<<<");
        logger.info("");
    }

    private PreAuthorizationToken(SocialProviders providers, SocialLoginDto dto) {
        super(providers, dto);
    }

    public PreAuthorizationToken(FormLoginDto dto) {
        this(dto.getId(), dto.getPassword());
    }

    public PreAuthorizationToken(SocialLoginDto dto) {
        this(dto.getProvider(), dto);
    }

    public String getUsername() {
        return (String)super.getPrincipal();
    }

    public String getUserPassword() {
        return (String)super.getCredentials();
    }

}
