package com.financetracking.controllers;

import com.financetracking.controllers.dtos.IncomeDto;
import com.financetracking.controllers.forms.IncomeForm;
import com.financetracking.models.Income;
import com.financetracking.repositories.IncomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import javax.validation.Valid;
import java.net.URI;
import java.util.stream.Collectors;

@RestController("/income")
public class IncomeController {

    @Autowired
    IncomeRepository incomeRepository;

    @GetMapping("/income")
    public List<IncomeDto> listAll() {
        List<Income> incomes = incomeRepository.findAll();
        return incomes.stream().map(IncomeDto::new).collect(Collectors.toList());
    }

    @PostMapping("/income")
    public ResponseEntity<IncomeDto> registry(@RequestBody @Valid IncomeForm form, UriComponentsBuilder uriComponentsBuilder) {
        Income income = form.convert(form);
        incomeRepository.save(income);

        URI uri = uriComponentsBuilder.path("income/{id}").buildAndExpand(income.getId()).toUri();
        return ResponseEntity.created(uri).body(new IncomeDto(income));
    }

}
