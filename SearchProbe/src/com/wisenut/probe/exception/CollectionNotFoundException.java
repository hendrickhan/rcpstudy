package com.wisenut.probe.exception;

public class CollectionNotFoundException extends Exception {
	private static final long serialVersionUID = 3202161846568059029L;

	public CollectionNotFoundException() {
		super("Not founde collection configurations.");
	}
	
	public CollectionNotFoundException(String message) {
		super(message);
	}
}
