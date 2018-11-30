package kr.hsz.domain.account;

import java.util.List;

public interface AccountService {
	
    Account get(String userId);

    List<Account> getAll();

    Account update(Account account);
    
    Account create(Account account);

}
