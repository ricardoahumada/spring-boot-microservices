package com.netmind.productsservice.persistence;

import com.netmind.productsservice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductsRepository extends JpaRepository<Product, Long> {
    List<Product> findByNameContaining(String name);
}
