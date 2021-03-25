package com.project.demo.service;

import com.project.demo.model.Expense;

import java.util.List;
import java.util.Map;
public interface ExpenseService {
    Map<String, List<Expense>> sortAndGroup(List<Expense> expenseList);
}
