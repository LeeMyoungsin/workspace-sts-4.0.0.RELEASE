package kr.hsz.audit;
/*
 * 다음을 위해 남겨 둠
 * */
//import java.util.Optional;

//import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

//import kr.hsz.domain.account.Account;

@Component
public class SecurityAuditorAware {	// implements AuditorAware<Account> {

//	@Override
//	public Optional<Account> getCurrentAuditor() {
//		
////		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
////		if(authentication instanceof AnonymousAuthenticationToken) return Optional.empty();
////		PostAuthorizationToken postAuthorizationToken = (PostAuthorizationToken) authentication;
//		
////		return Optional.ofNullable(accountService.findByEmail(postAuthorizationToken.getAccountContext().getAccount().getEmail()));
//		
//		Account rtn = new Account();
//		
//		return Optional.ofNullable(rtn);
//		
//		
//	}

}
