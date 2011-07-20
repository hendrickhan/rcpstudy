package com.wisenut.probe.exception;

public class NotAllowedLocationException extends Exception {
	private static final long serialVersionUID = -4977936619722890822L;
	
	public NotAllowedLocationException() {
		super("Location argument value is not allowed.");
	}
	
	public NotAllowedLocationException(String message) {
		super(message);
	}

}
