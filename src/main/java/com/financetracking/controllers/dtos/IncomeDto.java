package com.financetracking.controllers.dtos;

import com.financetracking.models.Income;

import java.math.BigDecimal;
import java.time.LocalDate;

public class IncomeDto {
    private Long id;
    private String description;
    private BigDecimal value;
    private LocalDate date;

    public IncomeDto(Income income) {
        this.id = income.getId();
        this.description = income.getDescription();
        this.value = income.getValue();
        this.date = income.getDate();
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getValue() {
        return value;
    }

    public LocalDate getDate() {
        return date;
    }
}
