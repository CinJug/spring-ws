package com.illuminationworksllc.samples.coffeeshop.endpoint;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.illuminationworksllc.samples.coffeeshop.domain.ShopProduct;
import com.illuminationworksllc.samples.coffeeshop.exception.ProductNotFoundException;
import com.illuminationworksllc.samples.coffeeshop.schema.Product;
import com.illuminationworksllc.samples.coffeeshop.schema.ProductSearchRequest;
import com.illuminationworksllc.samples.coffeeshop.schema.ProductSearchResponse;
import com.illuminationworksllc.samples.coffeeshop.service.CoffeeShopService;

@Endpoint
public class CoffeeShopEndpoint {
	private CoffeeShopService coffeeShopService;
	
	@Autowired
	public CoffeeShopEndpoint(CoffeeShopService coffeeShopService) {
		this.coffeeShopService = coffeeShopService;
	}
	
	@PayloadRoot(namespace="http://coffeeshop.com/schemas/messages", localPart="ProductSearchRequest")
	public @ResponsePayload ProductSearchResponse productInfo(@RequestPayload ProductSearchRequest productSearchRequest) throws ProductNotFoundException {
		List<Product> products = new ArrayList<Product>();
		for(ShopProduct shopProduct : coffeeShopService.findShopProductsByName(productSearchRequest.getProductName())) {
			Product product = new Product();
			product.setId(shopProduct.getId());
			product.setName(shopProduct.getName());
			product.setPrice(shopProduct.getPrice());
			products.add(product);
		}
		ProductSearchResponse productSearchResponse = new ProductSearchResponse();
		productSearchResponse.getProduct().addAll(products);
		return productSearchResponse;
	}
}
