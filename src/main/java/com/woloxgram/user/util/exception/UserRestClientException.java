package com.woloxgram.user.util.exception;

public class UserRestClientException extends RuntimeException {

	private static final long serialVersionUID = 7424253696674981843L;

	public UserRestClientException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public UserRestClientException(String message) {
		super(message);
	}
}
