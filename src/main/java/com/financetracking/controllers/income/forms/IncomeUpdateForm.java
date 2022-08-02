package com.financetracking.controllers.income.forms;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.financetracking.models.Income;
import com.financetracking.repositories.IncomeRepository;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

public class IncomeUpdateForm {
    @NotNull @NotEmpty @Length(min = 4)
    private String description;
    @NotNull @NumberFormat(style = NumberFormat.Style.CURRENCY)
    private BigDecimal value;
    @NotNull @DateTimeFormat(pattern = "dd/MM/yyyy") @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate date;

    public Income convert(IncomeUpdateForm form) {
        return new Income(description, value, date);
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

    public Income update(Long id, IncomeRepository incomeRepository) {
        Income income = incomeRepository.getReferenceById(id);
        income.setDescription(description);
        income.setValue(value);
        income.setDate(date);
        return income;
    }
}
