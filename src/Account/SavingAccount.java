package Account;

import people.Owner;

public class SavingAccount extends BankAccount {
    private static final double INTEREST_RATE = 0.02; // 2 %

    private String accounttype = "Saving Account";

    @Override
    public String getAccountType() {
        return accounttype;
    }
    public SavingAccount(Owner owner) {
        super(owner);
    }
    public SavingAccount(Owner owner, double balance) {
        super(owner, balance);
    }

    public SavingAccount(Owner owner, String accountnum) {
        super(owner, accountnum, 0);
    }

    @Override
    public void calculateInterest() {
        double interest = this.getBalance() * INTEREST_RATE;
        this.setBalance(this.getBalance() + interest);
    }
}
