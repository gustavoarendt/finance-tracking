package com.financetracking.controllers.income.forms;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.financetracking.models.Income;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

public class IncomeForm {
    @NotNull @NotEmpty @Length(min = 4)
    private String description;
    @NotNull @NumberFormat(style = NumberFormat.Style.CURRENCY)
    private BigDecimal value;
    @NotNull @DateTimeFormat(pattern = "dd/MM/yyyy") @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate date;

    public Income convert(IncomeForm form) {
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
}
