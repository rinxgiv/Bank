package transfers;

import Account.BankAccount;
import Account.BusinessAccount;
import Account.StudentAccount;
import factory.TransactionFactory;

public class WithdrawTransferService {

    private static final double BUSINESS_ACCOUNT_SERVICE_FEE = 0.01;

    private final TransferLoggerService transferLoggerService;

    private final TransactionFactory transactionFactory;

    public WithdrawTransferService(TransferLoggerService transferLoggerService, TransactionFactory transactionFactory) {
        this.transferLoggerService = transferLoggerService;
        this.transactionFactory = transactionFactory;
    }

    public void withdraw(BankAccount account, double amount) {
        double newBalance = account.getBalance() - amount;
        double serviceFee = 0;

        if (account instanceof BusinessAccount) {
            serviceFee = amount * BUSINESS_ACCOUNT_SERVICE_FEE;

            newBalance -= serviceFee;
        }

        if (newBalance < getWithDrawLimit(account)) {
            throw new IllegalArgumentException("Cannot subtract negative amount");
        }

        account.setBalance(newBalance);

        transferLoggerService.log(transactionFactory.createWithdraw(account, amount, serviceFee));
    }

    private int getWithDrawLimit(BankAccount account) {
        if (account instanceof StudentAccount) {
            return -5000;
        }

        return 0;
    }

}