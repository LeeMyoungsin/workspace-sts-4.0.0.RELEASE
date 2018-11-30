package kr.hsz.security.providers;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import kr.hsz.domain.account.Account;
import kr.hsz.domain.account.AccountRepository;
import kr.hsz.domain.account.AccountService;
import kr.hsz.security.tokens.PostAuthorizationToken;
import kr.hsz.security.tokens.PreAuthorizationToken;
import kr.hsz.security.user.UserImpl;

@Component
public class FormLoginAuthenticationProvider implements AuthenticationProvider {

    @Autowired private PasswordEncoder passwordEncoder;
    @Autowired AccountService accountService;
    @Autowired AccountRepository accountRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        PreAuthorizationToken token = (PreAuthorizationToken)authentication;

        String username = token.getUsername();
        String password = token.getUserPassword();

        Account account = accountService.get(username);
        if(account==null) new NoSuchElementException("정보에 맞는 계정이 없습니다.");
        
        if(isCorrectPassword(password, account)) {
            return PostAuthorizationToken.getTokenFromUserImpl(new UserImpl(account));
        }

        throw new NoSuchElementException("인증 정보가 정확하지 않습니다.");
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return PreAuthorizationToken.class.isAssignableFrom(aClass);
    }

    private boolean isCorrectPassword(String password, Account account) {
        return passwordEncoder.matches(password, account.getPassword());
    }
}
