package factory;

import Account.BankAccount;
import Account.Mena;
import transactions.Transaction;
import transactions.TransactionType;

import java.time.LocalDateTime;
import java.util.UUID;

public class TransactionFactory {

    public Transaction createDeposit(BankAccount to, double amount) {
        return create(TransactionType.DEPOSIT, null, to.getAccountnum(), amount, 0, to.getMena());
    }

    public Transaction createWithdraw(BankAccount from, double amount, double fee) {
        return create(TransactionType.WITHDRAW, from.getAccountnum(), null, amount, fee, from.getMena());
    }

    public Transaction createTransfer(BankAccount from, BankAccount to, double amount, double fee) {
        return create(TransactionType.TRANSFER, from.getAccountnum(), to.getAccountnum(), amount, fee, from.getMena());
    }

    private Transaction create(TransactionType type, String fromAccountNum, String toAccountNum,
                               double amount, double fee, Mena mena) {
        return new Transaction(UUID.randomUUID().toString(), type, fromAccountNum, toAccountNum,
                amount, fee, mena, LocalDateTime.now());
    }

}
