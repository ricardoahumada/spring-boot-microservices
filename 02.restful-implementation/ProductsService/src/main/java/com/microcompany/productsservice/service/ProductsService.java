package com.microcompany.productsservice.service;

import com.microcompany.productsservice.exception.ProductNotfoundException;
import com.microcompany.productsservice.model.Product;
import com.microcompany.productsservice.persistence.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class ProductsService {
    @Autowired
    private ProductsRepository productsRepository;

    @PersistenceContext
    EntityManager em;

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

    public Product duplicate(Long id) {
        Product currProd = productsRepository.findById(id).orElseThrow(() -> new ProductNotfoundException("El recurso no existe"));
        em.detach(currProd);
        currProd.setId(null);
        return productsRepository.save(currProd);
//        Product newProduct = new Product(null, currProd.getName(), currProd.getSerial());
//        return productsRepository.save(newProduct);
    }
}
