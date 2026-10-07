package transfers;

import Account.BankAccount;
import Account.StudentAccount;
import factory.TransactionFactory;

public class DepositTransferService {

    private static final double STUDENT_ACCOUNT_DEPOSIT_BONUS = 0.005;

    private final TransferLoggerService transferLoggerService;

    private final TransactionFactory transactionFactory = new TransactionFactory();

    public DepositTransferService(TransferLoggerService transferLoggerService) {
        this.transferLoggerService = transferLoggerService;
    }

    public void deposit(BankAccount bankAccount, double amount) {
        double newBalance = bankAccount.getBalance() + amount;

        if (bankAccount instanceof StudentAccount) {
            double depositBonus = amount * STUDENT_ACCOUNT_DEPOSIT_BONUS;

            newBalance += depositBonus;
        }

        bankAccount.setBalance(newBalance);

        transferLoggerService.log(transactionFactory.createDeposit(bankAccount, amount));
    }

}
