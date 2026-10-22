package factory;

import Account.BankAccount;
import transactions.Transaction;
import transactions.TransactionType;

import java.time.LocalDateTime;
import java.util.UUID;

public class TransactionFactory {

    public Transaction createDeposit(BankAccount to, double amount) {
        return create(TransactionType.DEPOSIT, null, to.getAccountnum(), amount, 0);
    }

    public Transaction createWithdraw(BankAccount from, double amount, double fee) {
        return create(TransactionType.WITHDRAW, from.getAccountnum(), null, amount, fee);
    }

    public Transaction createTransfer(BankAccount from, BankAccount to, double amount, double fee) {
        return create(TransactionType.TRANSFER, from.getAccountnum(), to.getAccountnum(), amount, fee);
    }

    private Transaction create(TransactionType type, String fromAccountNum, String toAccountNum,
                               double amount, double fee) {
        return new Transaction(UUID.randomUUID().toString(), type, fromAccountNum, toAccountNum,
                amount, fee, LocalDateTime.now());
    }

}
