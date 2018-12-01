package kr.hsz.security.providers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import kr.hsz.domain.account.Account;
import kr.hsz.domain.account.AccountService;
import kr.hsz.exception.PasswordFailExceptionImpl;
import kr.hsz.exception.UserIdNotFoundExceptionImpl;
import kr.hsz.security.tokens.PostAuthorizationToken;
import kr.hsz.security.tokens.PreAuthorizationToken;
import kr.hsz.security.user.UserImpl;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class FormLoginAuthenticationProvider implements AuthenticationProvider {

    @Autowired private PasswordEncoder passwordEncoder;
    @Autowired AccountService accountService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    	logger.info(">>>>>>>>>>>>>>>>authenticate");
        PreAuthorizationToken token = (PreAuthorizationToken)authentication;

        String username = token.getUsername();
        String password = token.getUserPassword();

        Account account = accountService.get(username);
        if(account==null) throw new UserIdNotFoundExceptionImpl(username);
        
        if(isCorrectPassword(password, account)) {
            return PostAuthorizationToken.getTokenFromUserImpl(new UserImpl(account));
        }else {
        	throw new PasswordFailExceptionImpl(username, password);
        }
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return PreAuthorizationToken.class.isAssignableFrom(aClass);
    }

    private boolean isCorrectPassword(String password, Account account) {
        return passwordEncoder.matches(password, account.getPassword());
    }
}
