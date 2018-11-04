package com.psl.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class StockUndeflowException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public StockUndeflowException(String msg) {
		super(msg);
	}
}
