package com.illuminationworksllc.samples.coffeeshop.domain;

import java.math.BigDecimal;

public class Coffee implements ShopProduct {
	private final int id;
	private final String name;
	private final BigDecimal price;

	public Coffee(int id, String name, BigDecimal price) {
		this.id = id;
		this.name = name;
		this.price = price;
	}

	@Override
	public int getId() {
		return id;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public BigDecimal getPrice() {
		return price;
	}
}
