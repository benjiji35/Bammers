package com.bam.GESTIBANKBAM.util;

public class BAMException extends Exception {

	public BAMException() {
		super();
	}

	public BAMException(String message) {
		super(message);
	}

	public BAMException(Throwable cause) {
		super(cause);
	}

	public BAMException(String message, Throwable cause) {
		super(message, cause);
	}

	public BAMException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
