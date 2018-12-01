package kr.hsz.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class PasswordFailExceptionImpl extends RuntimeException {

	private static final long serialVersionUID = -6427675368445536655L;

	private String message;

	public PasswordFailExceptionImpl(String userId, String password) {
		super(userId);
		String message = String.format("사용자 ID [%s]의 암호 [%s]가 일치 하지 않습니다..", userId, password);
		this.message = message;
	}
	
}
