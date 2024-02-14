package com.microcompany.productsservice.service;

import com.microcompany.productsservice.model.Product;
import com.microcompany.productsservice.persistence.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductsService {
    @Autowired
    private ProductsRepository productsRepository;

    public Product actualizar(Long id, Product producto) {
        if (producto != null && producto.getId().equals(id)) {
            // actualizar campos
            return productsRepository.save(producto);
        } else {
            throw new RuntimeException();
        }

    }

    public List<Product> getProductsByText(String text) {
        // valida texto, ....
        return productsRepository.findByNameContaining(text);
    }
}
