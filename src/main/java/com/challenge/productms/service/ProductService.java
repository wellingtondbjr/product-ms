package com.challenge.productms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.challenge.productms.model.Product;
import com.challenge.productms.repository.ProductRepository;

@Service
public class ProductService {
	@Autowired
	ProductRepository productRepository;

	public Boolean isNewProduct(String name) {
		Product newProduct = productRepository.getProductByName(name);
		if (newProduct == null) {
			return Boolean.TRUE;
		} else {
			return Boolean.FALSE;
		}
	}

	public Product createProduct(Product product) {
		Boolean isNewProduct = this.isNewProduct(product.getName());
		if (Boolean.TRUE.equals(isNewProduct)) {
			productRepository.saveAndFlush(product);
			Product newProduct = productRepository.getProductByName(product.getName());
			return newProduct;
		} else {
			Product oldProduct = productRepository.getProductByName(product.getName());
			oldProduct.setName(product.getName());
			oldProduct.setDescription(product.getDescription());
			oldProduct.setPrice(product.getPrice());
			productRepository.save(oldProduct);
			return oldProduct;
		}
	}

	public Product updateProduct(Product product, String id) {
		Product updatedProduct = productRepository.getProduct(id);
		if (updatedProduct != null) {
			updatedProduct.setName(product.getName());
			updatedProduct.setDescription(product.getDescription());
			updatedProduct.setPrice(product.getPrice());
			productRepository.save(updatedProduct);
			return updatedProduct;
		} else {
			return null;
		}
	}

	public Product getProduct(String id) {
		Product product = productRepository.getProduct(id);
		return product;
	}
	
	public List<Product> getProductList() {
		List<Product> productList = productRepository.findAll();
		return productList;
	}
	
	public List<Product> getProduct(Double min_price, Double max_price, String q) {
		List<Product> productList = productRepository.getProduct(min_price, max_price, q);
		return productList;
	}

	public Boolean deleteProduct(String id) {
		Product deleteProduct = productRepository.getProduct(id);
		if (deleteProduct != null) {
			productRepository.deleteProduct(id);
			return Boolean.TRUE;
		} else {
			return Boolean.FALSE;
		}
	}
}
