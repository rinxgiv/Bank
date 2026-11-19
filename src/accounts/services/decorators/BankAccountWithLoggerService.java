package accounts.services.decorators;

import Account.BankAccount;
import accounts.services.IBankAccountService;
import people.Owner;

import java.util.List;

public class BankAccountWithLoggerService implements IBankAccountService {

    protected final IBankAccountService bankAccountService;

    public BankAccountWithLoggerService(IBankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    @Override
    public BankAccount createCurrentAccount(Owner owner, double balance) {
        logStart();
        BankAccount bankAccount = bankAccountService.createCurrentAccount(owner, balance);
        logEnd();

        return bankAccount;
    }

    @Override
    public BankAccount createStudentAccount(Owner owner, double balance, String school) {
        logStart();
        BankAccount bankAccount = bankAccountService.createStudentAccount(owner, balance, school);
        logEnd();

        return bankAccount;
    }

    @Override
    public BankAccount createSavingAccount(Owner owner) {
        logStart();
        BankAccount bankAccount = bankAccountService.createSavingAccount(owner);
        logEnd();

        return bankAccount;
    }

    @Override
    public BankAccount createBusinessAccount(Owner owner, double balance) {
        logStart();
        BankAccount bankAccount = bankAccountService.createBusinessAccount(owner, balance);
        logEnd();

        return bankAccount;
    }

    @Override
    public List<BankAccount> getBankAccounts() {
        return bankAccountService.getBankAccounts();
    }

    protected void logStart() {
        System.out.println("Create Bank Account started");
    }

    protected void logEnd() {
        System.out.println("Create Bank Account finished");
    }
}
