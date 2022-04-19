package com.revature.exceptions;

public class UserIsNotFinanceManagerException extends RuntimeException{

	public UserIsNotFinanceManagerException() {
		super();
	}

	public UserIsNotFinanceManagerException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public UserIsNotFinanceManagerException(String message, Throwable cause) {
		super(message, cause);
	}

	public UserIsNotFinanceManagerException(String message) {
		super(message);
	}

	public UserIsNotFinanceManagerException(Throwable cause) {
		super(cause);
	}
	
	
	
	
}
