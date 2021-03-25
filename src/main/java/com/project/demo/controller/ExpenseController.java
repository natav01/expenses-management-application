package com.project.demo.controller;

import com.project.demo.model.Expense;
import com.project.demo.service.implementation.ExpenseServiceImpl;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class ExpenseController {
    private final ExpenseServiceImpl expenseService;
    private int counter = 1;
    private List<Expense> expenseList = new ArrayList<>();

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/expenses")
    @ApiOperation(value = "adds expense entry to the list of user\n" +
            "expenses")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "expense added", response = Expense.class),
            @ApiResponse(code = 404, message = "Non-create event")
    })
    public Expense addExpense(@RequestBody @Validated Expense request) {
        request.setId(counter++);
        expenseList.add(request);
        return request;
    }

    @GetMapping(value = "/expenses")
    public Map<String, List<Expense>> getAllExpenses() {
        return expenseService.sortAndGroup(expenseList);
    }

    @DeleteMapping(value = "/expenses")
    public List<Expense> delete(@RequestParam("date") String date) {
        List<Expense> needToDelete = getExpense(date);
        expenseList.removeAll(needToDelete);
        return needToDelete;
    }

    private List<Expense> getExpense(String date) {
        return expenseList.stream()
                .filter(i -> i.getDate().equals(date)).collect(Collectors.toList());
    }

    public List<Expense> getExpenses() {
        return expenseList;
    }
}
