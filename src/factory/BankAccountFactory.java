package factory;

import Account.BankAccount;
import Account.BusinessAccount;
import Account.CurrentAccount;
import Account.SavingAccount;
import Account.StudentAccount;
import accounts.services.AccountNumberGenerator;
import notifiers.Notifiers;
import people.Owner;

import java.util.UUID;

public class BankAccountFactory {

    private final AccountNumberGenerator accountNumberGenerator;

    private final Notifiers notifier;

    public BankAccountFactory(AccountNumberGenerator accountNumberGenerator, Notifiers notifier) {
        this.accountNumberGenerator = accountNumberGenerator;
        this.notifier = notifier;
    }

    public CurrentAccount createCurrentAccount(Owner owner, double balance) {
        CurrentAccount currentAccount = new CurrentAccount(owner, balance);
        initBankAccount(currentAccount);
        return currentAccount;
    }

    public StudentAccount createStudentAccount(Owner owner, double balance, String school) {
        StudentAccount studentAccount = new StudentAccount(owner, null, balance, school);
        initBankAccount(studentAccount);
        return studentAccount;
    }

    public SavingAccount createSavingAccount(Owner owner) {
        SavingAccount savingAccount = new SavingAccount(owner);
        initBankAccount(savingAccount);
        return savingAccount;
    }

    public BusinessAccount createBusinessAccount(Owner owner, double balance) {
        BusinessAccount businessAccount = new BusinessAccount(owner, balance);
        initBankAccount(businessAccount);
        return businessAccount;
    }

    private void initBankAccount(BankAccount bankAccount) {
        bankAccount.setUuid(UUID.randomUUID().toString());
        bankAccount.setAccountnum(accountNumberGenerator.generate());
        bankAccount.setNotifier(notifier);
    }

}
