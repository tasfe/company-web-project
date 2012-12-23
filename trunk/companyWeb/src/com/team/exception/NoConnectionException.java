package com.team.exception;

/**
 * 
 * @author Allen
 *
 */
public class NoConnectionException extends Exception {

	private static final long serialVersionUID = -5290432208651609290L;
	
	public NoConnectionException(){
		super();
	}
	
	public NoConnectionException(String message){
		super(message);
	}
	
	public NoConnectionException(Throwable cause){
		super(cause);
	}
	
	public NoConnectionException(String message,Throwable cause){
		super(message,cause);
	}

}
