package com.mvppoa.adidas.exceptions;

import org.zalando.problem.ThrowableProblem;

public class ConnectionServerException extends ThrowableProblem {

	private static final long serialVersionUID = 1L;

	private String message;

	public ConnectionServerException() {
        super();
    }

	public ConnectionServerException(String message) {
		super();
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}


}
