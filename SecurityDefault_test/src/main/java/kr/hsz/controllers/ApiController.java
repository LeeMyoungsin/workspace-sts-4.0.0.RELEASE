package kr.hsz.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.hsz.security.tokens.PostAuthorizationToken;

@RestController
@RequestMapping("/api")
public class ApiController {


    @GetMapping("/hello")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String getUsername(Authentication authentication) {

        PostAuthorizationToken token = (PostAuthorizationToken)authentication;

        return token.getUserImpl().getUsername();
    }


}
