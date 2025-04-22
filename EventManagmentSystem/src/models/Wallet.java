package models;

import data.IDGenerator;
import managers.Identifiable;

public class Wallet implements Identifiable {
    private double balance;
    private String walletID;

    // ARGUMENT CONSTRUCTOR
    public Wallet(double balance) {
        this.balance = balance;
        generateID();
    }

    // GETTER
    public double getBalance() {
        return balance;
    }

    // DEPOSIT FUNCTION
    public void deposit(double amount) {
        if (amount > 0)
            balance += amount;
    }

    // WITHDRAW FUNCTION
    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            return true; // Operation successful
        }
        return false; // Operation failed
    }

    @Override
    public String toString() {
        return "Wallet Balance: " + balance;
    }

    @Override
    public String getIdPrefix() {
        return "WLT";
    }

    @Override
    public void generateID() {
        this.walletID = IDGenerator.generate(this);
    }
}
