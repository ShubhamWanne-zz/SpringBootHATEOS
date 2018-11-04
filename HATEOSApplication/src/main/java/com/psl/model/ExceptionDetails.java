package com.psl.model;

import java.util.Date;

import org.springframework.hateoas.Link;

import com.psl.controller.AlbumController;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class ExceptionDetails {
	private Date timestamp;
	private String message;
	private String details;
	private Link support_uri;
	
	public ExceptionDetails(Date timestamp,String message,String details) {
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
		setSupport_uri(linkTo(methodOn(AlbumController.class).getAllAlbums()).withSelfRel());
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public Link getSupport_uri() {
		return support_uri;
	}

	public void setSupport_uri(Link support_uri) {
		this.support_uri = support_uri;
	}
	
}
