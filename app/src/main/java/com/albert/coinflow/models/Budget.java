package com.albert.coinflow.models;

import org.parceler.Parcel;

/** This is a model that deals with the data stored to Firebase */
@Parcel
public class Budget {
    private String date;
    private String expenses;
    private String moneySaved;
    private String income;
    private String total;
    private String pushId;

    public Budget() {}

    public Budget(String date, String expenses, String moneySaved) {
        this.date = date;
        this.expenses = expenses;
        this.moneySaved = moneySaved;
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
        Integer calculateTotal = Integer.parseInt(moneySaved) - Integer.parseInt(expenses);
        total = calculateTotal.toString();
        return total;
    }

    public String getMoneySaved() {
        return moneySaved;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getPushId() {
        return pushId;
    }

    public void setPushId(String pushId) {
        this.pushId = pushId;
    }
}
