package kr.hsz.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class UserIdNotFoundExceptionImpl extends RuntimeException {

	private static final long serialVersionUID = -5278964714622478858L;
	
	private String message;

	public UserIdNotFoundExceptionImpl(String userId) {
		super(userId);
		String message = String.format("사용자 ID [%s]는 존재 하지 않습니다.", userId);
		this.message = message;
	}
	
}
