package accounts.services;

import Account.BankAccount;
import factory.BankAccountFactory;
import people.Owner;

import java.util.ArrayList;
import java.util.List;

public class BankAccountService implements IBankAccountService {

    private final BankAccountFactory bankAccountFactory;

    private final List<BankAccount> bankAccounts = new ArrayList<>();

    public BankAccountService(BankAccountFactory bankAccountFactory) {
        this.bankAccountFactory = bankAccountFactory;
    }

    @Override
    public BankAccount createCurrentAccount(Owner owner, double balance) {
        return register(bankAccountFactory.createCurrentAccount(owner, balance));
    }

    @Override
    public BankAccount createStudentAccount(Owner owner, double balance, String school) {
        return register(bankAccountFactory.createStudentAccount(owner, balance, school));
    }

    @Override
    public BankAccount createSavingAccount(Owner owner) {
        return register(bankAccountFactory.createSavingAccount(owner));
    }

    @Override
    public BankAccount createBusinessAccount(Owner owner, double balance) {
        return register(bankAccountFactory.createBusinessAccount(owner, balance));
    }

    @Override
    public List<BankAccount> getBankAccounts() {
        return bankAccounts;
    }

    private BankAccount register(BankAccount bankAccount) {
        bankAccounts.add(bankAccount);
        return bankAccount;
    }
}
