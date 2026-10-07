package transfers;

import Account.BankAccount;
import Account.BusinessAccount;
import factory.TransactionFactory;

public class TransferService {

    private static final double BUSINESS_ACCOUNT_TRANSFER_FEE = 0.003;

    private final TransferLoggerService transferLoggerService;

    private final TransactionFactory transactionFactory = new TransactionFactory();

    public TransferService(TransferLoggerService transferLoggerService) {
        this.transferLoggerService = transferLoggerService;
    }

    public void transfer(BankAccount from, BankAccount to, double amount) {
        if (from == null || to == null) {
            throw new IllegalArgumentException("Account must not be null");
        }

        if (from == to) {
            throw new IllegalArgumentException("Cannot transfer to the same account");
        }

        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }

        double fee = 0;
        if (from instanceof BusinessAccount) {
            fee = amount * BUSINESS_ACCOUNT_TRANSFER_FEE;
        }

        double totalWithdrawn = amount + fee;

        if (from.getBalance() < totalWithdrawn) {
            throw new IllegalArgumentException("Insufficient funds");
        }

        from.setBalance(from.getBalance() - totalWithdrawn);
        to.setBalance(to.getBalance() + amount);

        transferLoggerService.log(transactionFactory.createTransfer(from, to, amount, fee));
    }

}
