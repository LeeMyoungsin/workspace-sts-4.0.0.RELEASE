package kr.hsz.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class RuntimeExceptionImpl extends RuntimeException {

	private static final long serialVersionUID = -1328908366079186627L;
	
	private String message;

	public RuntimeExceptionImpl(String msg) {
		super(msg);
		this.message = msg;
	}
	
}
