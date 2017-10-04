package com.albert.coinflow.models;

import org.parceler.Parcel;

/** This is a model that deals with the data stored to Firebase */
@Parcel
public class Budget {
    private String date;
    private String expenses;
    private String income;
    private String total;

    public Budget() {}

    public Budget(String date, String expenses, String income, String total) {
        this.date = date;
        this.expenses = expenses;
        this.income = income;
        this.total = total;
    }

    public String getDate() {
        return date;
    }

    public String getExpenses() {
        return expenses;
    }

    public String getIncome() {
        return income;
    }

    public String getTotal() {
        Integer calculateTotal = Integer.parseInt(income) - Integer.parseInt(expenses);
        total = calculateTotal.toString();
        return total;
    }
}
