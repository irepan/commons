package com.itappservices.commons.util.exception;

public class QueueFullException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7706412171851674182L;

	public QueueFullException() {
		super();
	}

	public QueueFullException(String message) {
		super(message);
	}

	public QueueFullException(String message, Throwable cause){
        super(message, cause);
	}
}
