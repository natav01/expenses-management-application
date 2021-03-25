package com.project.demo.service.implementation;

import com.project.demo.helpstool.Convertor;
import com.project.demo.model.Expense;
import com.project.demo.service.ExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service
public class ExchangeServiceImplementation implements ExchangeService {
    @Autowired
    private Convertor convertor;

    @Override
    public double getTotalExpensesInBase(List<Expense> expensesList,
                                         String base) {
        Map<String, Double> sumInCurrency = new TreeMap<>(String::compareTo);
        for (Expense ex : expensesList) {
            if (sumInCurrency.containsKey(ex.getCurrency())) {
                sumInCurrency.put(ex.getCurrency(),
                        sumInCurrency.get(ex.getCurrency()) + ex.getAmount());
            } else {
                sumInCurrency.put(ex.getCurrency(), ex.getAmount());
            }
        }
        sumInCurrency.replaceAll((k, v) -> v = exchange(k, base, v));
        return sumInCurrency.entrySet()
                .stream()
                .map(Map.Entry::getValue)
                .mapToDouble(Double::doubleValue)
                .sum();
    }

    public double exchange(String from, String to, double amount) {
        Map<String, Double> currencyValues = convertor.getExchangeValues();
        return amount * currencyValues.get(to) / currencyValues.get(from);
    }
}
