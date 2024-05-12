package com.jose.spring.springbootcrud.repositories;

import org.springframework.data.repository.CrudRepository;

import com.jose.spring.springbootcrud.entities.Product;

public interface ProductRepository extends CrudRepository<Product, Long>{

}
