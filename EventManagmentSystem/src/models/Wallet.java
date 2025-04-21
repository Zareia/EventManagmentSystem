package models;

import data.IDGenerator;
import managers.Identifiable;

public class Wallet implements Identifiable {
    private double balance;
    private String walletID;

    // ARGUMENT CONSTRUCTOR
    public Wallet(double balance){
        this.balance=balance;
        generateID();
    }

    // getter
    public double getBalance() {
        return balance;
    }

    // DEPOSIT FUNCTION
    public void deposit (double amount){
        if (amount > 0)
            balance = balance + amount;
    }

    // WITHDRAW FUNCTION
    public boolean  withdraw(double amount) {
        if (amount > 0 && amount <= balance){
            balance = balance - amount;
            return true; // operation succesful
        }
        return false;  // operation fail
    }

    @Override
    public String toString() {
        return "your balance is " + balance ;
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
