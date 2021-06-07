package com.challenge.productms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.productms.errorHandling.RequestErrorResponse;
import com.challenge.productms.model.Product;
import com.challenge.productms.service.ProductService;

@RestController
@CrossOrigin("*")
public class ProductController {
	@Autowired
	private ProductService productService;
	
	@PostMapping("/products")
	public ResponseEntity<Object> createProduct(@RequestBody Product product) {
		// if all required fields are provided correctly
		if (product != null && product.getName() != null && product.getDescription() != null
				&& product.getPrice() != null && product.getPrice() > 0D) {
			try {
				Product newProduct = productService.createProduct(product);
				return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
			} catch (Exception e) {
				// error processing the operation on database
				e.printStackTrace();
				RequestErrorResponse errorResponse = new RequestErrorResponse(500,
						"Error creating the product, please try again in a few minutes");
				return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
			}

		} else {
			RequestErrorResponse errorResponse = new RequestErrorResponse(400,
					"Product name, description and price cannot be null and price must be positive");
			return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/products/{id}")
	public ResponseEntity<Object> updateProduct(@RequestBody Product product, @PathVariable String id) {
		// if all required fields are provided correctly
		if (id != null && product != null && product.getName() != null && product.getDescription() != null
				&& product.getPrice() != null && product.getPrice() > 0D) {
			try {
				Product newProduct = productService.updateProduct(product, id);
				if (newProduct != null) {
					return new ResponseEntity<>(newProduct, HttpStatus.OK);
				} else {
					RequestErrorResponse errorResponse = new RequestErrorResponse(404, "Product not found");
					return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
				}
			} catch (Exception e) {
				// error processing the operation on database
				e.printStackTrace();
				RequestErrorResponse errorResponse = new RequestErrorResponse(500,
						"Error updating the product, please try again in a few minutes");
				return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
			}

		} else {
			RequestErrorResponse errorResponse = new RequestErrorResponse(400,
					"Product name, description and price cannot be null and price must be positive");
			return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/products/{id}")
	public ResponseEntity<Object> deleteProduct(@PathVariable String id) {
		if (id != null && id.length() > 0) {
			try {
				Boolean response = productService.deleteProduct(id);
				if (Boolean.TRUE.equals(response)) {
					return new ResponseEntity<>("Product deleted successfully", HttpStatus.OK);
				} else {
					RequestErrorResponse errorResponse = new RequestErrorResponse(404, "Product not found");
					return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
				}
			} catch (Exception e) {
				// error processing the operation on database
				e.printStackTrace();
				RequestErrorResponse errorResponse = new RequestErrorResponse(500,
						"Error deleting the product, please try again in a few minutes");
				return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
			}

		} else {
			RequestErrorResponse errorResponse = new RequestErrorResponse(400, "Product id cannot be null or empty");
			return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/products/{id}")
	public ResponseEntity<Object> getProduct(@PathVariable String id) {
		if (id != null && id.length() > 0) {
			try {
				Product product = productService.getProduct(id);
				if (product != null) {
					return new ResponseEntity<>(product, HttpStatus.OK);
				} else {
					return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
				}
			} catch (Exception e) {
				// error processing the operation on database
				e.printStackTrace();
				RequestErrorResponse errorResponse = new RequestErrorResponse(500,
						"Error deleting the product, please try again in a few minutes");
				return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} else {
			RequestErrorResponse errorResponse = new RequestErrorResponse(400, "Product id cannot be null or empty");
			return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/products")
	public ResponseEntity<Object> getProduct() {
		try {
			List<Product> productList = productService.getProductList();
			if (productList != null && productList.size() > 0) {
				return new ResponseEntity<>(productList, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			// error processing the operation on database
			e.printStackTrace();
			RequestErrorResponse errorResponse = new RequestErrorResponse(500,
					"Error deleting the product, please try again in a few minutes");
			return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/products/search")
	public ResponseEntity<Object> searchProduct(@RequestParam Double min_price, @RequestParam Double max_price, @RequestParam String q) {
		if (min_price != null && min_price >= 0 && max_price != null && max_price >= 0 && q != null && q.length() > 0) {
			try {
				List<Product> productList = productService.getProduct(min_price, max_price, q);
				if (productList != null && productList.size() > 0) {
					return new ResponseEntity<>(productList, HttpStatus.OK);
				} else {
					return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
				}
			} catch (Exception e) {
				// error processing the operation on database
				e.printStackTrace();
				RequestErrorResponse errorResponse = new RequestErrorResponse(500,
						"Error deleting the product, please try again in a few minutes");
				return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} else {
			RequestErrorResponse errorResponse = new RequestErrorResponse(400, "Min, max and q cannot be null or lower than 0");
			return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
		}
	}

}
