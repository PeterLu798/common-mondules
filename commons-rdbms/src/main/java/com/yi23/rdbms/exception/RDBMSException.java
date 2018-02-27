package com.yi23.rdbms.exception;

public class RDBMSException extends RuntimeException {
	private static final long serialVersionUID = -5034463949539478898L;

	public RDBMSException() {
	}

	public RDBMSException(String msg) {
		super(msg);
	}
}
