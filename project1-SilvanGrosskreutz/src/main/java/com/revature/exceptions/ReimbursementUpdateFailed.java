package com.revature.exceptions;

public class ReimbursementUpdateFailed extends RuntimeException{

	public ReimbursementUpdateFailed() {
		super();
	}

	public ReimbursementUpdateFailed(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ReimbursementUpdateFailed(String message, Throwable cause) {
		super(message, cause);
	}

	public ReimbursementUpdateFailed(String message) {
		super(message);
	}

	public ReimbursementUpdateFailed(Throwable cause) {
		super(cause);
	}
	
	
}
