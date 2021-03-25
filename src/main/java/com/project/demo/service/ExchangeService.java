package com.project.demo.service;

import com.project.demo.model.Expense;

import java.util.List;

public interface ExchangeService {
    double getTotalExpensesInBase(List<Expense> expensesList, String base);
}
