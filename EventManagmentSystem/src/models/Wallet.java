package models;

public class Wallet {
    private double balance;

    public Wallet(double balance) {
        this.balance = balance;
        
    }

    // GETTERs
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
}
