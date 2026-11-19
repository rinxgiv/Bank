package accounts.services;

import Account.BankAccount;
import people.Owner;

import java.util.List;

public interface IBankAccountService {

    BankAccount createCurrentAccount(Owner owner, double balance);

    BankAccount createStudentAccount(Owner owner, double balance, String school);

    BankAccount createSavingAccount(Owner owner);

    BankAccount createBusinessAccount(Owner owner, double balance);

    List<BankAccount> getBankAccounts();
}
