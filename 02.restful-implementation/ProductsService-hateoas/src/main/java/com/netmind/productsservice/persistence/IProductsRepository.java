package com.netmind.productsservice.persistence;

import com.netmind.productsservice.entity.ProductEntity;

import java.util.List;
import java.util.Optional;

public interface IProductsRepository {
    List<ProductEntity> findAll();

    Optional<ProductEntity> findById(Long id);

    List<ProductEntity> findByNameContaining(String name);

    ProductEntity findByName(String name);

    ProductEntity save(ProductEntity aProductEntity);

    void deleteById(Long id);
}
