package com.banana.bananamint.persistence;

import com.banana.bananamint.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.SQLException;
import java.util.List;

public interface AccountDataRepository extends JpaRepository<Account, Long> {

}
