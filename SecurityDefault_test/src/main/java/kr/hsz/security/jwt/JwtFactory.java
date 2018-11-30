package kr.hsz.security.jwt;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import kr.hsz.domain.account.Account;
import kr.hsz.domain.role.Role;
import kr.hsz.security.user.UserImpl;

@Component
public class JwtFactory {

	public static final CharSequence delimiter = "\n";
	
    private static final Logger logger = LoggerFactory.getLogger(JwtFactory.class);

    private static String signingKey = "jwttest";

    public String generateToken(UserImpl userImpl) {

        String token = null;
        Account account = userImpl.getAccount();
        List<Role> roles = account.getRoles();

        try {
            token = JWT.create()
                    .withIssuer("ww.hsz.kr")
                    .withClaim("USERNAME", account.getUserId())
                    .withClaim("USER_ROLE", roles.stream().map(r -> r.getName()).collect(Collectors.joining(delimiter)))	//.withClaim("USER_ROLE", context.getAccount().getUserRole().getRoleName())
                    .sign(generateAlgorithm());

        } catch (Exception e) {
        	logger.error(e.getMessage());
        }

        return token;
    }

    private Algorithm generateAlgorithm() throws UnsupportedEncodingException {
        return Algorithm.HMAC256(signingKey);
    }

}
