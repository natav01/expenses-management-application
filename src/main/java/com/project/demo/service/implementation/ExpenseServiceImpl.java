package com.project.demo.service.implementation;

import com.project.demo.model.Expense;
import com.project.demo.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExpenseServiceImpl implements ExpenseService {

    @Override
    public Map<String, List<Expense>> sortAndGroup(List<Expense> expenseList) {
        List<Expense> sort = expenseList.stream().sorted(Comparator.comparing(Expense::getDate)).collect(Collectors.toList());
        Map<String, List<Expense>> map = sort.stream().collect(Collectors.groupingBy(Expense::getDate));
        return new TreeMap<>(map);
    }
}
