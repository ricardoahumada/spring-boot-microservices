package com.netmind.productsservice.persistence;

import com.netmind.productsservice.entity.ProductEntity;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public class JPAProductsRepository implements IProductsRepository{

    @PersistenceContext
    EntityManager em;

    @Override
    public List<ProductEntity> findAll() {
        Query query = em.createQuery("SELECT p FROM Product p");
        return (List<ProductEntity>) query.getResultList();
    }

    @Override
    public Optional<ProductEntity> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<ProductEntity> findByNameContaining(String name) {
        return null;
    }

    @Override
    public ProductEntity findByName(String name) {
        return null;
    }

    @Override
    @Transactional
    public ProductEntity save(ProductEntity aProductEntity) {
        em.persist(aProductEntity);
        return aProductEntity;
    }

    @Override
    public void deleteById(Long id) {

    }
}
