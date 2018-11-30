//package kr.hsz.security.providers;
//
//import java.util.UUID;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.stereotype.Component;
//
//import kr.hsz.domain.account.Account;
//import kr.hsz.domain.account.AccountRepository;
//import kr.hsz.domain.account.SocialProviders;
//import kr.hsz.domain.account.UserRole;
//import kr.hsz.dto.SocialLoginDto;
//import kr.hsz.security.AccountContext;
//import kr.hsz.security.services.specification.SocialFetchService;
//import kr.hsz.security.social.SocialUserProperty;
//import kr.hsz.security.tokens.PostAuthorizationToken;
//import kr.hsz.security.tokens.SocialPreAuthorizationToken;
//
//@Component
//public class SocialLoginAuthenticationProvider implements AuthenticationProvider {
//
//    @Autowired
//    private AccountRepository accountRepository;
//
//    @Qualifier("socialFetchServiceProd")
//    @Autowired
//    private SocialFetchService service;
//
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        SocialPreAuthorizationToken token = (SocialPreAuthorizationToken)authentication;
//        SocialLoginDto dto = token.getDto();
//
//        return PostAuthorizationToken.getTokenFromAccountContext(AccountContext.fromAccountModel(getAccount(dto)));
//    }
//
//    @Override
//    public boolean supports(Class<?> aClass) {
//        return SocialPreAuthorizationToken.class.isAssignableFrom(aClass);
//    }
//
//    private Account getAccount(SocialLoginDto dto) {
//        SocialUserProperty property = service.getSocialUserInfo(dto);
//
//        String userId = property.getUserId();
//        SocialProviders provider = dto.getProvider();
//
//        return accountRepository.findBySocialIdAndSocialProvider(Long.valueOf(userId), provider)
//                .orElseGet(() -> accountRepository.save(
//                        new Account(null, property.getUserNickname(), "SOCIAL_USER", String.valueOf(UUID.randomUUID().getMostSignificantBits()), UserRole.USER, Long.valueOf(property.getUserId()), provider, property.getProfileHref())));
//
//    }
//
//}
