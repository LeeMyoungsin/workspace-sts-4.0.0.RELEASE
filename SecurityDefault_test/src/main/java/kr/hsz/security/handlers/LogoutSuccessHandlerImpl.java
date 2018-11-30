package kr.hsz.security.handlers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Service;

import kr.hsz.domain.account.Account;
import kr.hsz.security.user.UserImpl;

@Service
public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler {

	private static final Logger logger = LoggerFactory.getLogger(LogoutSuccessHandlerImpl.class);
	
	public LogoutSuccessHandlerImpl() {
		logger.info("");
		logger.info(">>>>> LogoutSuccessHandlerImpl <<<<<");
		logger.info("");
	}
	
	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		
		if (authentication != null && authentication.getDetails() != null) {
			try {
				UserImpl userImpl = (UserImpl) authentication.getPrincipal();
				Account account = userImpl.getAccount();
				logger.info("LogOut Success... [member] {}", account.getUserId());
				request.getSession().invalidate();
            } catch (Exception e) {
                e.printStackTrace();
            }
		}
		response.setStatus(HttpStatus.OK.value());
		response.sendRedirect("/");
	}

}
