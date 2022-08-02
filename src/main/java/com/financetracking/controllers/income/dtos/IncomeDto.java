package com.financetracking.controllers.income.dtos;

import com.financetracking.models.Income;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.time.LocalDate;

public class IncomeDto {
    private final Long id;
    private final String description;
    private final BigDecimal value;
    private final LocalDate date;

    public IncomeDto(Income income) {
        this.id = income.getId();
        this.description = income.getDescription();
        this.value = income.getValue();
        this.date = income.getDate();
    }

    public static Page<IncomeDto> convert(Page<Income> incomes) {
        return incomes.map(IncomeDto::new);
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
