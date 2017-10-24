package com.itappservices.commons.util.exception;

public class QueueEmptyException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1397985692554616827L;
	public QueueEmptyException(){
        super();
    }
     
    public QueueEmptyException(String message){
        super(message);
    }
     
    public QueueEmptyException(String message, Throwable cause){
        super(message, cause);
    }
}
