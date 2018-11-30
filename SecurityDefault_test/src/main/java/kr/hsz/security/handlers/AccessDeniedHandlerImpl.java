package kr.hsz.security.handlers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Service;

@Service
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
	
	private static final Logger logger = LoggerFactory.getLogger(AccessDeniedHandlerImpl.class);
	
	@Autowired
	public AccessDeniedHandlerImpl() {
		
		logger.info("");
		logger.info(">>>>> AccessDeniedHandlerImpl <<<<<");
		logger.info("");
		
		
	}
	
	@Override
	public void handle(HttpServletRequest req, HttpServletResponse res, AccessDeniedException ade)
			throws IOException, ServletException {

		logger.info("AccessDeniedHandler ==> Message : {}",ade.getMessage());
		logger.info("AccessDeniedHandler ==> Exceiption : {}",ade);
		logger.info("AccessDeniedHandler ==> LocalizedMessage : {}",ade.getLocalizedMessage());
		logger.info("AccessDeniedHandler ==> StackTrace : {}", ade.getStackTrace().toString());
		
		req.setAttribute("errMsg",ade.getMessage());
		req.getRequestDispatcher("/").forward(req, res);
		
	}

}
