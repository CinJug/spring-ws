package com.illuminationworksllc.samples.coffeeshop.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.illuminationworksllc.samples.coffeeshop.domain.Coffee;
import com.illuminationworksllc.samples.coffeeshop.domain.ShopProduct;
import com.illuminationworksllc.samples.coffeeshop.exception.ProductNotFoundException;

@Service
public class CoffeeShopService {
	private static final List<Coffee> COFFEE = new ArrayList<Coffee>();
	static {
		COFFEE.add(new Coffee(1, "Espresso", new BigDecimal("1.99")));
		COFFEE.add(new Coffee(2, "Caffe Latte", new BigDecimal("2.99")));
		COFFEE.add(new Coffee(3, "Cappuccino", new BigDecimal("3.99")));
		COFFEE.add(new Coffee(4, "Caffe Mocha", new BigDecimal("4.99")));
	}
	
	public List<ShopProduct> findShopProductsByName(final String namePart) throws ProductNotFoundException {
		List<ShopProduct> coffees = new ArrayList<ShopProduct>();
		for(Coffee coffee : COFFEE) {
			if(coffee.getName().startsWith(namePart)) {
				coffees.add(coffee);
			}
		}
		if(coffees.isEmpty()) {
			throw new ProductNotFoundException(namePart);
		}
		return coffees;
	}
}
