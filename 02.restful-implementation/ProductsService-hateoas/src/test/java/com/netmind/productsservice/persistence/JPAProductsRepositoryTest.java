package com.netmind.productsservice.persistence;

import com.netmind.productsservice.entity.ProductEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@DataJpaTest()
@ComponentScan(basePackages = {"com.netmind.productsservice.persistence"})
@AutoConfigureTestEntityManager
class JPAProductsRepositoryTest {

    private static final Logger logger = LoggerFactory.getLogger(JPAProductsRepositoryTest.class);

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
//    private IProductsRepository jpaRepo;
    private ProductsRepository jpaRepo;

    @Test
    void findAll() {
        // given
        ProductEntity aProductEntity = new ProductEntity(null, "Fake Product", "123-123-1234");
        entityManager.persist(aProductEntity);
        entityManager.flush();

        // when
        List<ProductEntity> prods = jpaRepo.findAll();
        logger.info("Prods:" + prods);

        // then
        assertThat(prods.size())
                .isGreaterThan(0);

        assertNotNull(prods);
    }

    //    @Test
    void findById() {
    }

    //    @Test
    void findByNameContaining() {
    }

    //    @Test
    void findByName() {
    }

    @Test
    void save() {
        // given
        ProductEntity aProductEntity = new ProductEntity(null, "Another Fake Product", "123-123-1234");

        // when
        jpaRepo.save(aProductEntity);

        System.out.println(aProductEntity);

        // then
        assertThat(aProductEntity.getId()).isGreaterThan(0);
    }

    @Test
    void deleteById() {
    }
}