package com.psl.controller;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.psl.exceptions.StockUndeflowException;
import com.psl.model.ExceptionDetails;

@ControllerAdvice
@RestController
public class CustomizedResponseExceptionHandler extends ResponseEntityExceptionHandler{
		
		@ExceptionHandler(StockUndeflowException.class)
		public final ResponseEntity<ExceptionDetails> handleStockUnderflowException
															(StockUndeflowException ex, WebRequest req){
			ExceptionDetails exceptionDetails = new ExceptionDetails(new Date(),"Stock not available !" , "Please stay connected until the new stock arrives.");
			req.getDescription(true);
			return new ResponseEntity<>(exceptionDetails, HttpStatus.NOT_FOUND);
		}
}
