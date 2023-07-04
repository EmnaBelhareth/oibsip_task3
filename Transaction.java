package Task_3;

public class Transaction {
    public enum Type {
        DEPOSIT, WITHDRAWAL
    }

    private Type type;
    private double amount;

    public Transaction(Type type, double amount) {
        this.type = type;
        this.amount = amount;
    }

    public Type getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }
}

