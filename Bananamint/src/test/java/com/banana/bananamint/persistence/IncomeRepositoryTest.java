package com.banana.bananamint.persistence;

import ch.qos.logback.classic.pattern.LineOfCallerConverter;
import com.banana.bananamint.domain.Account;
import com.banana.bananamint.domain.Customer;
import com.banana.bananamint.domain.Income;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest()
@ComponentScan(basePackages = {"com.banana.bananamint.persistence"})
@AutoConfigureTestEntityManager
class IncomeRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    IncomeDataRepository incomeDataRepository;

    @Test
    void given_an_account_When_findAll_thenEmpty() {
        Customer customer = new Customer(1L);
        Account account = new Account(1L);

        Income new_income = new Income(null, customer, 1000.0, LocalDate.now(), account, "finished");

        entityManager.persist(new_income);
        System.out.println("***** new_income:" + new_income);

        customer = entityManager.find(Customer.class, 1L);
        System.out.println("***** customer:" + customer);

        account = entityManager.find(Account.class, 1L);
        System.out.println("***** account:" + account);

        List<Income> incomes = incomeDataRepository.findAll();
        assertThat(incomes).isNotNull();
        assertThat(incomes.size()).isGreaterThan(0);

    }

    @Test
    void given_a_new_account_When_save_thenOk() {
        Customer customer = new Customer(1L);
        Account account = new Account(1L);

        Income new_income = new Income(null, customer, 1000.0, LocalDate.now(), account, "finished");

        incomeDataRepository.save(new_income);

        assertThat(new_income).isNotNull();
        assertThat(new_income.getId()).isGreaterThan(0);
    }
}