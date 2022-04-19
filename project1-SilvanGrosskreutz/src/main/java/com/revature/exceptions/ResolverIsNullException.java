package com.revature.exceptions;

public class ResolverIsNullException extends RuntimeException {

	public ResolverIsNullException() {
	}

	public ResolverIsNullException(String message) {
		super(message);
	}

	public ResolverIsNullException(Throwable cause) {
		super(cause);
	}

	public ResolverIsNullException(String message, Throwable cause) {
		super(message, cause);
	}

	public ResolverIsNullException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
