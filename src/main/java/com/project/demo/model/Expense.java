package com.project.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.TimeZone;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Expense {
    private static final SimpleDateFormat dateFormat
            = new SimpleDateFormat("yyyy-MM-dd");

    Integer id;
    @JsonFormat(pattern = "yyyy-MM-dd")
    String date;
    Double amount;
    String currency;
    String product;

    public void setDate(Date date) {
        dateFormat.setTimeZone(TimeZone.getTimeZone("Europe/Kiev"));
        this.date = dateFormat.format(date);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Expense)) return false;
        Expense expense = (Expense) o;
        return Objects.equals(id, expense.id) &&
                Objects.equals(date, expense.date) &&
                Objects.equals(amount, expense.amount) &&
                Objects.equals(currency, expense.currency) &&
                Objects.equals(product, expense.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, amount, currency, product);
    }

    @Override
    public String toString() {
        return "Expense{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", amount=" + amount +
                ", currency='" + currency + '\'' +
                ", product='" + product + '\'' +
                '}';
    }
}
