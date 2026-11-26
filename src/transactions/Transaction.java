package transactions;

import Account.Mena;

import java.time.LocalDateTime;

public class Transaction {

    private final String uuid;

    private final TransactionType type;

    // u vkladu je null (peníze přišly zvenku)
    private final String fromAccountNum;

    // u výběru je null (peníze odešly ven)
    private final String toAccountNum;

    private final double amount;

    private final double fee;

    private final Mena mena;

    private final LocalDateTime createdAt;

    public Transaction(String uuid, TransactionType type, String fromAccountNum, String toAccountNum,
                       double amount, double fee, Mena mena, LocalDateTime createdAt) {
        this.uuid = uuid;
        this.type = type;
        this.fromAccountNum = fromAccountNum;
        this.toAccountNum = toAccountNum;
        this.amount = amount;
        this.fee = fee;
        this.mena = mena;
        this.createdAt = createdAt;
    }

    public String getUuid() {
        return uuid;
    }

    public TransactionType getType() {
        return type;
    }

    public String getFromAccountNum() {
        return fromAccountNum;
    }

    public String getToAccountNum() {
        return toAccountNum;
    }

    public double getAmount() {
        return amount;
    }

    public double getFee() {
        return fee;
    }

    public Mena getMena() {
        return mena;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public String toString() {
        return createdAt + " " + type + " " + fromAccountNum + " -> " + toAccountNum
                + " amount: " + amount + " " + mena + " fee: " + fee + " " + mena;
    }
}
