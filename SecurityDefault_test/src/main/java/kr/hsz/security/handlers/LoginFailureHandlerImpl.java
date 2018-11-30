package kr.hsz.security.handlers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Service;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Data
public class LoginFailureHandlerImpl implements AuthenticationFailureHandler {

//	private static final Logger logger = LoggerFactory.getLogger(LoginFailureHandlerImpl.class);
	
	private String userIdName;
	private String userPwName;
	private String loginRedirectName;
	private String exceptionMsgName;
	private String defaultFailureUrl;
	
	public LoginFailureHandlerImpl(){
		this.userIdName = "userId";
		this.userPwName = "userPassword";
		this.loginRedirectName = "loginRedirect";
		this.exceptionMsgName = "exceptionmsg";
		this.defaultFailureUrl = "/login?ng";
		
		logger.info("");
		logger.info(">>>>> LoginFailureHandlerImpl <<<<<");
		logger.info("");
	}
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse res, AuthenticationException authException)
			throws IOException, ServletException {

		logger.info(authException.getMessage());
		logger.debug(authException.getLocalizedMessage());
		
		for(StackTraceElement s : authException.getStackTrace()){
			String info = String.format("ClassName[%s] - FileName[%s] - MethodName[%s] : LineNum[%s] / NativeMethod[%s]"
							, s.getClassName(), s.getFileName(), s.getMethodName(), s.getLineNumber() , s.isNativeMethod());
			logger.debug(info);
		}
		String userId = req.getParameter(this.userIdName);
		String userPw = req.getParameter(this.userPwName);
		String loginRedirect = req.getParameter(this.loginRedirectName);
		
		req.setAttribute(this.userIdName, userId);
		req.setAttribute(this.userPwName, userPw);
		req.setAttribute(this.loginRedirectName, loginRedirect);
		req.setAttribute(exceptionMsgName, authException.getMessage());
		
		req.getRequestDispatcher(defaultFailureUrl).forward(req, res);
	}

}
