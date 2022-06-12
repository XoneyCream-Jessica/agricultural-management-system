package com.ams.abnormal;

/**
 * 业务异常处理
 */
public class AmsException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public AmsException(String message) {
		super(message);
	}

	public AmsException(Throwable cause) {
		super(cause);
	}

	public AmsException(String message, Throwable cause) {
		super(message, cause);
	}
}