package com.project.demo.controller;

import com.project.demo.model.Expense;
import com.project.demo.service.ExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("total")
public class ExchangeController {
    @Autowired
    private ExchangeService exchangeService;

    @Autowired
    private ExpenseController expenseController;

    @GetMapping
    ResponseEntity<Double> showAllExpenses(@RequestParam("base") String base) {

        List<Expense> expenses = expenseController.getExpenses();
        return new ResponseEntity<>(
                exchangeService.getTotalExpensesInBase(expenses, base),
                HttpStatus.OK);
    }
}
