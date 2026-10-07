package factory;

import Account.BusinessAccount;
import Account.CurrentAccount;
import Account.SavingAccount;
import Account.StudentAccount;
import accounts.AccountNumberService;
import people.Owner;

public class BankAccountFactory {

    private final AccountNumberService accountNumberService = new AccountNumberService();

    public CurrentAccount createCurrentAccount(Owner owner, double balance) {
        return new CurrentAccount(owner, accountNumberService.generate(), balance);
    }

    public StudentAccount createStudentAccount(Owner owner, double balance, String school) {
        return new StudentAccount(owner, accountNumberService.generate(), balance, school);
    }

    public SavingAccount createSavingAccount(Owner owner) {
        return new SavingAccount(owner, accountNumberService.generate());
    }

    public BusinessAccount createBusinessAccount(Owner owner, double balance) {
        BusinessAccount businessAccount = new BusinessAccount(owner, balance);
        businessAccount.setAccountnum(accountNumberService.generate());
        return businessAccount;
    }

}
