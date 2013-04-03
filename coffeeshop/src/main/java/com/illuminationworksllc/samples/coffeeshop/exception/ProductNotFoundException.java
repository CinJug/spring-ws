package com.illuminationworksllc.samples.coffeeshop.exception;

public class ProductNotFoundException extends Exception {
	private static final long serialVersionUID = -8182225622944288926L;
	
	public ProductNotFoundException(final String namePart) {
		super(String.format("Could not find product starting with %s.", namePart));
	}
}
