package factory;

import Account.BankAccount;
import Account.BusinessAccount;
import Account.CurrentAccount;
import Account.Mena;
import Account.SavingAccount;
import Account.StudentAccount;
import accounts.services.AccountNumberGenerator;
import currency.MoneyFormatter;
import notifiers.Notifiers;
import people.Owner;

import java.util.UUID;

public class BankAccountFactory {

    private final AccountNumberGenerator accountNumberGenerator;

    private final Notifiers notifier;

    private final MoneyFormatter moneyFormatter;

    private final Mena defaultMena;

    public BankAccountFactory(AccountNumberGenerator accountNumberGenerator, Notifiers notifier,
                              MoneyFormatter moneyFormatter, Mena defaultMena) {
        this.accountNumberGenerator = accountNumberGenerator;
        this.notifier = notifier;
        this.moneyFormatter = moneyFormatter;
        this.defaultMena = defaultMena;
    }

    public CurrentAccount createCurrentAccount(Owner owner, double balance) {
        return createCurrentAccount(owner, balance, defaultMena);
    }

    public CurrentAccount createCurrentAccount(Owner owner, double balance, Mena mena) {
        CurrentAccount currentAccount = new CurrentAccount(owner, balance);
        initBankAccount(currentAccount, mena);
        return currentAccount;
    }

    public StudentAccount createStudentAccount(Owner owner, double balance, String school) {
        return createStudentAccount(owner, balance, school, defaultMena);
    }

    public StudentAccount createStudentAccount(Owner owner, double balance, String school, Mena mena) {
        StudentAccount studentAccount = new StudentAccount(owner, null, balance, school);
        initBankAccount(studentAccount, mena);
        return studentAccount;
    }

    public SavingAccount createSavingAccount(Owner owner) {
        return createSavingAccount(owner, defaultMena);
    }

    public SavingAccount createSavingAccount(Owner owner, Mena mena) {
        SavingAccount savingAccount = new SavingAccount(owner);
        initBankAccount(savingAccount, mena);
        return savingAccount;
    }

    public BusinessAccount createBusinessAccount(Owner owner, double balance) {
        return createBusinessAccount(owner, balance, defaultMena);
    }

    public BusinessAccount createBusinessAccount(Owner owner, double balance, Mena mena) {
        BusinessAccount businessAccount = new BusinessAccount(owner, balance);
        initBankAccount(businessAccount, mena);
        return businessAccount;
    }

    private void initBankAccount(BankAccount bankAccount, Mena mena) {
        bankAccount.setUuid(UUID.randomUUID().toString());
        bankAccount.setAccountnum(accountNumberGenerator.generate());
        bankAccount.setNotifier(notifier);
        bankAccount.setMoneyFormatter(moneyFormatter);
        bankAccount.setMena(mena);
    }

}
