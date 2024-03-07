package com.banana.bananamint.persistence;

import com.banana.bananamint.domain.Income;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IncomeDataRepository extends JpaRepository<Income, Integer> {

}
