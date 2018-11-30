//package kr.hsz.security.tokens;
//
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//
//import kr.hsz.dto.SocialLoginDto;
//
//public class SocialPreAuthorizationToken extends UsernamePasswordAuthenticationToken {
//
//	private static final long serialVersionUID = 1L;
//
//	public SocialPreAuthorizationToken(SocialLoginDto dto) {
//        super(dto.getProvider(), dto);
//    }
//
//    public SocialLoginDto getDto() {
//        return (SocialLoginDto)super.getCredentials();
//    }
//}
