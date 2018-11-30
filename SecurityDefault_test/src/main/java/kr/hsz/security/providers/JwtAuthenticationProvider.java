package kr.hsz.security.providers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import kr.hsz.security.jwt.JwtDecoder;
import kr.hsz.security.tokens.JwtPreProcessingToken;
import kr.hsz.security.tokens.PostAuthorizationToken;
import kr.hsz.security.user.UserImpl;

@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private JwtDecoder jwtDecoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String token = (String)authentication.getPrincipal();
        UserImpl userImpl = jwtDecoder.decodeJwt(token);

        return PostAuthorizationToken.getTokenFromUserImpl(userImpl);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return JwtPreProcessingToken.class.isAssignableFrom(aClass);
    }
}
