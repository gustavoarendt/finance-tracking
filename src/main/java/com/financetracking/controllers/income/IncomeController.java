package com.financetracking.controllers.income;

import com.financetracking.controllers.income.dtos.IncomeDetailedDto;
import com.financetracking.controllers.income.dtos.IncomeDto;
import com.financetracking.controllers.income.forms.IncomeForm;
import com.financetracking.controllers.income.forms.IncomeUpdateForm;
import com.financetracking.models.Income;
import com.financetracking.repositories.IncomeRepository;
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
@RequestMapping("/income")
public class IncomeController {

    @Autowired
    IncomeRepository incomeRepository;

    @GetMapping("")
    public Page<IncomeDto> listAll(@PageableDefault(sort = "id", direction = Sort.Direction.DESC, page = 0, size = 10) Pageable pagination) {
        Page<Income> incomes = incomeRepository.findAll(pagination);
        return IncomeDto.convert(incomes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<IncomeDetailedDto> detailedIncome(@PathVariable Long id) {
        Optional<Income> optional = incomeRepository.findById(id);
        return optional.map(income -> ResponseEntity.ok(new IncomeDetailedDto(income))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<IncomeDto> registry(@RequestBody @Valid IncomeForm form, UriComponentsBuilder uriComponentsBuilder) {
        Income income = form.convert(form);
        incomeRepository.save(income);

        URI uri = uriComponentsBuilder.path("income/{id}").buildAndExpand(income.getId()).toUri();
        return ResponseEntity.created(uri).body(new IncomeDto(income));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<IncomeDto> update(@RequestBody @Valid IncomeUpdateForm form, @PathVariable Long id) {
        Optional<Income> optional = incomeRepository.findById(id);
        if (optional.isPresent()) {
            Income income = form.update(id, incomeRepository);
            return ResponseEntity.ok(new IncomeDto(income));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<Income> optional = incomeRepository.findById(id);
        optional.map(income -> {
            incomeRepository.deleteById(id);
            return ResponseEntity.ok().build();
        });
        return ResponseEntity.notFound().build();
    }

}
