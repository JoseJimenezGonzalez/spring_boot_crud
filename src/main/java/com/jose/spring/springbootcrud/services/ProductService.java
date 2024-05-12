package com.jose.spring.springbootcrud.services;

import java.util.List;
import java.util.Optional;

import com.jose.spring.springbootcrud.entities.Product;

public interface ProductService {

    //Cuando se realiza consulta a la base de datos -> Optional
    //Ya que no se sabe si va a encontrar el objeto o no
    List<Product> findAll();
    Optional<Product> findById(Long id);
    Product save(Product product);
    Optional<Product> update(Long id, Product product);
    Optional<Product> delete(Long id);
}
