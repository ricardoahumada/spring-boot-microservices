package com.netmind.productsservice.controller;

import com.netmind.productsservice.entity.ProductEntity;
import com.netmind.productsservice.persistence.ProductsRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class ProductRepositoryIntegrationTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ProductsRepository productsRepository;

    @Test
    public void whenFindByName_thenReturnProduct() {
        // given
        ProductEntity aProductEntity = new ProductEntity(null, "Fake Product","123-123-1234");
        entityManager.persist(aProductEntity);
        entityManager.flush();

        // when
        ProductEntity found = productsRepository.findByName(aProductEntity.getName());

        // then
        assertThat(found.getName())
                .isEqualTo(aProductEntity.getName());
    }

}