package com.netmind.productsservice.persistence;

import com.netmind.productsservice.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductsRepository extends JpaRepository<ProductEntity, Long> {
    List<ProductEntity> findByNameContaining(String name);
    @Query("select p from ProductEntity p where p.name = ?1")
    ProductEntity findByName(String name);
}
