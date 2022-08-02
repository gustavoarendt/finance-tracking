package com.financetracking.controllers.expense.forms;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.financetracking.models.Expense;
import com.financetracking.repositories.ExpenseRepository;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

public class ExpenseUpdateForm {
    @NotNull @NotEmpty @Length(min = 4)
    private String description;
    @NotNull @NumberFormat(style = NumberFormat.Style.CURRENCY)
    private BigDecimal value;
    @NotNull @DateTimeFormat(pattern = "dd/MM/yyyy") @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate date;

    public Expense convert(ExpenseUpdateForm form) {
        return new Expense(description, value, date);
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

    public Expense update(Long id, ExpenseRepository expenseRepository) {
        Expense expense = expenseRepository.getReferenceById(id);
        expense.setDescription(description);
        expense.setValue(value);
        expense.setDate(date);
        return expense;
    }
}
