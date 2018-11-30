package kr.hsz.security.user;

import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import kr.hsz.domain.account.Account;
import kr.hsz.domain.account.AccountService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);
	
	@Autowired
	AccountService accountService;
	
	public UserDetailsServiceImpl() {
		logger.info("");
		logger.info(">>>>> UserDetailsService <<<<<");
		logger.info("");
	}
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		try {
			Account account = accountService.get(username);
			UserDetails userDetails = new UserImpl(account);
			return userDetails;
			
		}catch (Exception e) {
			new NoSuchElementException("아이디에 맞는 계정이 없습니다.");
		}
		
		return null;
	}

}
