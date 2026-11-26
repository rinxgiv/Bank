package transfers;

import Account.BankAccount;
import Account.BusinessAccount;
import currency.ExchangeRateService;
import factory.TransactionFactory;

public class TransferService {

    private static final double BUSINESS_ACCOUNT_TRANSFER_FEE = 0.003;

    private final TransferLoggerService transferLoggerService;

    private final TransactionFactory transactionFactory;

    private final ExchangeRateService exchangeRateService;

    public TransferService(TransferLoggerService transferLoggerService, TransactionFactory transactionFactory,
                           ExchangeRateService exchangeRateService) {
        this.transferLoggerService = transferLoggerService;
        this.transactionFactory = transactionFactory;
        this.exchangeRateService = exchangeRateService;
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
        double received = exchangeRateService.convert(amount, from.getMena(), to.getMena());
        to.setBalance(to.getBalance() + received);

        transferLoggerService.log(transactionFactory.createTransfer(from, to, amount, fee));
    }

}
