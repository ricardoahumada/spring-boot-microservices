package com.banana.bananamint.persistence;

import com.banana.bananamint.domain.Account;
import com.banana.bananamint.domain.Customer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest()
@ComponentScan(basePackages = {"com.banana.bananamint.persistence"})
@AutoConfigureTestEntityManager
class AccountRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    AccountDataRepository accountDataRepository;

    @Test
    void given_an_account_When_findAll_thenEmpty() {
        Customer customer = new Customer(1L);
        Account newAccount = new Account(null, "ahorro", LocalDate.now(), 200.0, 10.0, customer, true);

        entityManager.persist(newAccount);
        System.out.println("***** newAccount:" + newAccount);

        customer = entityManager.find(Customer.class, 1L);
        System.out.println("***** customer:" + customer);

        List<Account> accounts = accountDataRepository.findAll();
        assertThat(accounts).isNotNull();
        assertThat(accounts.size()).isGreaterThan(0);

    }

    @Test
    void given_a_new_account_When_save_thenOk() {
        Customer customer = new Customer(1L);
        Account newAccount = new Account(null, "ahorro", LocalDate.now(), 200.0, 10.0, customer, true);

        newAccount = accountDataRepository.save(newAccount);
        System.out.println("***** newAccount:" + newAccount);

        customer = entityManager.find(Customer.class, 1L);
        System.out.println("***** customer:" + customer);

        assertThat(newAccount).isNotNull();
        assertThat(newAccount.getId()).isGreaterThan(0);

    }
}