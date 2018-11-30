package kr.hsz.audit;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import net.bytebuddy.utility.RandomString;

@Component
public class CustomAuditorAware implements AuditorAware<String> {

	@Override
	public Optional<String> getCurrentAuditor() {
		String name = String.format("작성자:%s", RandomString.make(5));
		return Optional.ofNullable(name);
	}

}
