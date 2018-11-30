package kr.hsz.domain.account;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

	private AccountRepository rep;

	@Autowired
	public AccountServiceImpl(AccountRepository accountRepository) {
		this.rep = accountRepository;
	}
	
	@Override
	public Account get(String userId) {
		return rep.findAccountByUserId(userId);
	}

	@Override
	public List<Account> getAll() {
		return rep.findAll();
	}

	@Override
	public Account create(Account account) {
		return rep.save(account);

	}

	@Override
	public Account update(Account account) {
		
		if(account.getId()==null) return null;
		Account fetched = rep.getOne(account.getId());
		if(fetched == null) return null;
		
		if(account.getUsername() != null) fetched.setUsername(account.getUsername());
		if(account.getPassword() != null) fetched.setPassword(account.getPassword());
		if(account.getEmail() != null) fetched.setEmail(account.getEmail());
		if(account.getHp() != null) fetched.setHp(account.getHp());
		if(account.getTel() != null) fetched.setTel(account.getTel());
		if(account.getPost() != null) fetched.setPost(account.getPost());
		if(account.getAddress1() != null) fetched.setAddress1(account.getAddress1());
		if(account.getAddress2() != null) fetched.setAddress2(account.getAddress2());
		
		return rep.save(fetched);
	}

}
