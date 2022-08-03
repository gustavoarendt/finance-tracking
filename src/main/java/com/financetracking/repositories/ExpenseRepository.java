package com.financetracking.repositories;

import com.financetracking.models.Expense;
import com.financetracking.models.Income;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Month;
import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    Page<Expense> findAll(Pageable pagination);
    List<Expense> findByMonthAndDescription(Month month, String description);
}
