package com.revature.exceptions;

public class WrongPasswordOrUsernameException extends RuntimeException {

	public WrongPasswordOrUsernameException() {
	}

	public WrongPasswordOrUsernameException(String message) {
		super(message);
	}

	public WrongPasswordOrUsernameException(Throwable cause) {
		super(cause);
	}

	public WrongPasswordOrUsernameException(String message, Throwable cause) {
		super(message, cause);
	}

	public WrongPasswordOrUsernameException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
