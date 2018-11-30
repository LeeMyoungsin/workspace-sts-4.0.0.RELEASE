package kr.hsz.security.user;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import kr.hsz.domain.account.Account;
import kr.hsz.enums.EnableStatus;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserImpl extends User {

	private static final long serialVersionUID = 3272189717118898158L;

	//private static final boolean enabled = true; 
	private static final boolean accountNonExpired = true; 		//계정 만료되지 않음
	private static final boolean credentialsNonExpired = true; 	//계정이 잠겨 있지 않음
	private static final boolean accountNonLocked = true;		//계정 만료 기한 없음
	
	@Getter
	private Account account;
	
/*	
	public UserImpl(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}

	public UserImpl(String username, String password, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
	}
*/
	
	public UserImpl(Account account) {
		super(	account.getUserId(), account.getPassword(), account.getEnable().equals(EnableStatus.TRUE), 
				accountNonExpired, credentialsNonExpired, accountNonLocked, 
				parseAuthorities(account)
			);
		this.account = account;
	}
	
	private static List<SimpleGrantedAuthority> parseAuthorities(Account account) {
		logger.info("parseAuthorities <<<<<< ");
		
		List<SimpleGrantedAuthority> list = account
												.getRoles()
												.stream()
												.map(r -> new SimpleGrantedAuthority(r.getName()))
												.collect(Collectors.toList());
		
		for(SimpleGrantedAuthority sga : list) {
			logger.info("권한 리스트 : {}", sga.toString());
		}
		
		
		return list;
    }
	
	
}
