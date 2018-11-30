package kr.hsz.security.tokens;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import kr.hsz.security.filters.FormLoginFilter;
import kr.hsz.security.user.UserImpl;

public class PostAuthorizationToken extends UsernamePasswordAuthenticationToken {

	private static final Logger logger = LoggerFactory.getLogger(FormLoginFilter.class);
	
	private static final long serialVersionUID = -1009133462055849132L;

	private PostAuthorizationToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
        
    	logger.info("");
        logger.info(">>>>> PostAuthorizationToken <<<<<");
        logger.info("");
        
    }

    public UserImpl getUserImpl() {
		return (UserImpl) super.getPrincipal();
    }
    
    public static PostAuthorizationToken getTokenFromUserImpl(UserImpl userImpl) {
        return new PostAuthorizationToken(userImpl, userImpl.getPassword(), userImpl.getAuthorities());
    }
    
}
