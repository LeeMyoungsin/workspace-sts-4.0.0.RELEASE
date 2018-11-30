package kr.hsz.security.jwt;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import kr.hsz.domain.account.Account;
import kr.hsz.domain.role.Role;
import kr.hsz.security.exception.InvalidJwtException;
import kr.hsz.security.user.UserImpl;

@Component
public class JwtDecoder {

    private static final Logger logger = LoggerFactory.getLogger(JwtDecoder.class);

    public UserImpl decodeJwt(String token) {
        
    	DecodedJWT decodedJWT = isValidToken(token).orElseThrow(() -> new InvalidJwtException("유효한 토큰아 아닙니다."));
        String username = decodedJWT.getClaim("USERNAME").asString();
        String rolesString = decodedJWT.getClaim("USER_ROLE").asString();
        List<Role> roleList = new ArrayList<Role>();

        for(String str : rolesString.split(JwtFactory.delimiter.toString())) {
        	roleList.add(new Role(null, str, null));
        }
        
        Account account = new Account();
        account.setUserId(username);
        account.setRoles(roleList);
        
        return new UserImpl(account);
    }

    private Optional<DecodedJWT> isValidToken(String token) {

        DecodedJWT jwt = null;

        try {
            Algorithm algorithm = Algorithm.HMAC256("jwttest");
            JWTVerifier verifier = JWT.require(algorithm).build();

            jwt = verifier.verify(token);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        return Optional.ofNullable(jwt);
    }

}
