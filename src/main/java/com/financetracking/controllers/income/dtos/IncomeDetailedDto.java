package com.financetracking.controllers.income.dtos;

import com.financetracking.models.Income;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.time.LocalDate;

public class IncomeDetailedDto {
    private final Long id;
    private final String description;
    private final BigDecimal value;
    private final LocalDate date;

    public IncomeDetailedDto(Income income) {
        this.id = income.getId();
        this.description = income.getDescription();
        this.value = income.getValue();
        this.date = income.getDate();
    }

    public static Page<IncomeDetailedDto> convert(Page<Income> incomes) {
        return incomes.map(IncomeDetailedDto::new);
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
