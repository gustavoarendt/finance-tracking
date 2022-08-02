package com.financetracking.controllers.expense;

import com.financetracking.controllers.expense.dtos.ExpenseDetailedDto;
import com.financetracking.controllers.expense.dtos.ExpenseDto;
import com.financetracking.controllers.expense.forms.ExpenseForm;
import com.financetracking.controllers.expense.forms.ExpenseUpdateForm;
import com.financetracking.models.Expense;
import com.financetracking.repositories.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/expense")
public class ExpenseController {

    @Autowired
    ExpenseRepository expenseRepository;

    @GetMapping("")
    public Page<ExpenseDto> listAll(@PageableDefault(sort = "id", direction = Sort.Direction.DESC, page = 0, size = 10) Pageable pagination) {
        Page<Expense> expenses = expenseRepository.findAll(pagination);
        return ExpenseDto.convert(expenses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExpenseDetailedDto> detailedExpense(@PathVariable Long id) {
        Optional<Expense> optional = expenseRepository.findById(id);
        return optional.map(expense -> ResponseEntity.ok(new ExpenseDetailedDto(expense))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ExpenseDto> registry(@RequestBody @Valid ExpenseForm form, UriComponentsBuilder uriComponentsBuilder) {
        Expense expense = form.convert(form);
        expenseRepository.save(expense);

        URI uri = uriComponentsBuilder.path("expense/{id}").buildAndExpand(expense.getId()).toUri();
        return ResponseEntity.created(uri).body(new ExpenseDto(expense));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<ExpenseDto> update(@RequestBody @Valid ExpenseUpdateForm form, @PathVariable Long id) {
        Optional<Expense> optional = expenseRepository.findById(id);
        if (optional.isPresent()) {
            Expense expense = form.update(id, expenseRepository);
            return ResponseEntity.ok(new ExpenseDto(expense));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<Expense> optional = expenseRepository.findById(id);
        optional.map(expense -> {
            expenseRepository.deleteById(id);
            return ResponseEntity.ok().build();
        });
        return ResponseEntity.notFound().build();
    }

}
