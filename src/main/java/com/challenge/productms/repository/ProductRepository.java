package com.challenge.productms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.challenge.productms.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

	@Query(value= "select p from Product p where (p.description = :q or p.name = :q) and p.price >= :minPrice and p.price <= :maxPrice ")
	public List<Product> getProduct(@Param("minPrice") Double minPrice, @Param("maxPrice") Double maxPrice, @Param("q") String q);
	
	@Query(value= "select p from Product p where p.name = :name and p.description = :description")
	public Product getProduct(@Param("name") String name, @Param("description") String description);
	
	@Query(value = "select p from Product p where id = :id")
	public Product getProduct(@Param("id") String id);
	
	@Query(value = "select p from Product p where name = :name")
	public Product getProductByName(@Param("name") String name);
	
	@Transactional
	@Modifying
	@Query(value = "delete from Product where id = :id")
	public void deleteProduct(@Param("id") String id);
}
