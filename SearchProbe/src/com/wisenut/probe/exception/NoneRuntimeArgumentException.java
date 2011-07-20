package com.wisenut.probe.exception;

public class NoneRuntimeArgumentException extends Exception {
	private static final long serialVersionUID = 1L;

	public NoneRuntimeArgumentException() {
		super("No one runtime argument is running too.");
	}
	
	public NoneRuntimeArgumentException(String message) {
		super(message);
	}
}
