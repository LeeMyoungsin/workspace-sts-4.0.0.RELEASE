package kr.hsz.security.exception;

public class InvalidJwtException extends RuntimeException {

	private static final long serialVersionUID = 2971769065130575007L;

	public InvalidJwtException(String msg) {
        super(msg);
    }
}
