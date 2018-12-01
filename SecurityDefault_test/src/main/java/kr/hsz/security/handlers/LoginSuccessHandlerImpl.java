package kr.hsz.security.handlers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class LoginSuccessHandlerImpl implements AuthenticationSuccessHandler {

	private RequestCache requestCache = new HttpSessionRequestCache();
	private String targetUrlParameter;
	private String defaultUrl;
	private boolean useReferer;
	
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	@Autowired
	public LoginSuccessHandlerImpl(){
		targetUrlParameter = "loginRedirect";
		defaultUrl = "/";
		useReferer = false;
		
		logger.info("");
		logger.info(">>>>> LoginSuccessHandlerImpl <<<<<");
		logger.info("");
	}
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse res, Authentication auth)
			throws IOException, ServletException {
		logger.info(">>>>>>>>>>>>>>>LoginSuccessHandlerImpl");
/*		
		UserImpl userImpl = (UserImpl) auth.getPrincipal();
		Account account = userImpl.getAccount();
		
		member.setIpAddress(util.getClientIP(req));
		member.setLogInDate(new Date());
		
		logger.debug("member : {}", member.toString());
//		String oldCsrf = req.getParameter("_csrf");
		
		logger.debug("{}-{}-{}-{}"
						, auth.getName()
						, auth.getAuthorities().toString()
						, auth.getDetails().toString()
						, auth.getPrincipal().toString()
						, String.valueOf(securityUser.isEnabled()));
		for(GrantedAuthority a : auth.getAuthorities())	logger.debug(a.getAuthority());

//		String send_message = String.format("ID : %s / %s\n phone : %s", member.getUserId(), member.getUserName(), member.getHp());
		
		*/
		clearAuthenticationAttributes(req);
		
		logger.info(">>>>>>>>>>>>>>>LoginSuccessHandlerImpl2");
		
		switch(decideRedirectStrategy(req, res)) {
		case 1:
			useTargetUrl(req, res);
			break;
		case 2:
			useSessionUrl(req, res);
			break;
		case 3:
			useReferUrl(req, res);
			break;
		default:
			useDefaultUrl(req, res);
			break;
		}
//		res.sendRedirect(req.getContextPath()+"/");
	}
	
	private void useDefaultUrl(HttpServletRequest req, HttpServletResponse res) throws IOException {
		redirectStrategy.sendRedirect(req, res, defaultUrl);
	}

	private void useReferUrl(HttpServletRequest req, HttpServletResponse res) throws IOException {
		String targetUrl = req.getHeader("REFERER");
		redirectStrategy.sendRedirect(req, res, targetUrl);
	}

	private void useSessionUrl(HttpServletRequest req, HttpServletResponse res) throws IOException {
		SavedRequest savedRequest = requestCache.getRequest(req, res);
		String targetUrl = savedRequest.getRedirectUrl();
		
		logger.info(">>>>>>>>>useSessionUrl>>>>>>targetUrl : {}", targetUrl);
		
		redirectStrategy.sendRedirect(req, res, targetUrl);
	}

	private void useTargetUrl(HttpServletRequest req, HttpServletResponse res) throws IOException {
		SavedRequest savedRequest = requestCache.getRequest(req, res);
		if(savedRequest != null){
			requestCache.removeRequest(req, res);
		}
		String targetUrl = req.getParameter(targetUrlParameter);
		redirectStrategy.sendRedirect(req, res, targetUrl);
	}

	private int decideRedirectStrategy(HttpServletRequest req, HttpServletResponse res) {
		int result = 0;
		SavedRequest savedRequest = requestCache.getRequest(req, res);
		
		if(! "".equals(targetUrlParameter)){
			String targetUrl = req.getParameter(targetUrlParameter);
			if(StringUtils.hasText(targetUrl)){
				result = 1;
			}else{
				if(savedRequest != null){
					result = 2;
				}else{
					String referUrl = req.getHeader("referer");
					if(useReferer && StringUtils.hasText(referUrl)){
						result = 3;
					}else{
						result = 0;
					}
				}
			}
			logger.info(">>>>>>>>>>>>>>>result : {}", result);
			return result;
		}
		
		String refererUrl = req.getHeader("referer");
		if(useReferer && StringUtils.hasText(refererUrl)){
			result = 3;
		}else{
			result = 0;
		}
		return result;
	}

	public String getTargetUrlParameter() {
		return targetUrlParameter;
	}

	public void setTargetUrlParameter(String targetUrlParameter) {
		this.targetUrlParameter = targetUrlParameter;
	}

	public String getDefaultUrl() {
		return defaultUrl;
	}

	public void setDefaultUrl(String defaultUrl) {
		this.defaultUrl = defaultUrl;
	}

	public boolean isUseReferer() {
		return useReferer;
	}

	public void setUseReferer(boolean useReferer) {
		this.useReferer = useReferer;
	}

	private void clearAuthenticationAttributes(HttpServletRequest request){
		HttpSession session = request.getSession(false);
		if(session == null) return;
		session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
	}
	
	
	
}
