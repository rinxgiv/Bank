package Account;

import people.Owner;

public class CurrentAccount extends BankAccount {
    private static final double INTEREST_RATE = 0.001; // 0.1 %

    private String accounttype = "Current Account";

    @Override
    public String getAccountType() {
        return accounttype;
    }

    public CurrentAccount(Owner owner) {
        super(owner);
    }

    public CurrentAccount(Owner owner, double balance) {
        super(owner, balance);
    }

    public CurrentAccount(Owner owner, String accountnum, double balance) {
        super(owner, accountnum, balance);
    }

    @Override
    public void calculateInterest() {
        double interest = this.getBalance() * INTEREST_RATE;
        this.setBalance(this.getBalance() + interest);
    }
}
