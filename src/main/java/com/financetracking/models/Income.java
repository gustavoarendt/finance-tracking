package com.financetracking.models;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;

@Entity
@Table(name = "receitas")
public class Income {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "descricao")
    private String description;
    @Column(name = "valor")
    private BigDecimal value;
    @Column(name = "data")
    private LocalDate date;
    @Column(name = "mes")
    private Month month;

    public Income() {
    }

    public Income(String description, BigDecimal value, LocalDate date) {
        this.description = description;
        this.value = value;
        this.date = date;
        this.month = date.getMonth();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Month getMonth() {
        return month;
    }

    public void setMonth(Month month) {
        this.month = month;
    }
}
