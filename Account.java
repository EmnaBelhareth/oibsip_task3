package Task_3;

public class Account {
    private double balance;

    public  Account(){}
    public Account(double balance) {
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance)
    {
        this.balance=balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }
   
    public boolean withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }
}
    
    
