package com.financetracking.repositories;

import com.financetracking.models.Income;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IncomeRepository extends JpaRepository<Income, Long> {
    Page<Income> findAll(Pageable pagination);
}
