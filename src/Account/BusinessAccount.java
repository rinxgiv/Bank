package Account;

import people.Owner;

public class BusinessAccount extends BankAccount {
    private static final double INTEREST_RATE = 0.005; // 0.5 %

    private String accounttype = "Business Account";

    @Override
    public String getAccountType() {
        return accounttype;
    }
    public BusinessAccount(Owner owner) {
        super(owner);
    }

    public BusinessAccount(Owner owner, double balance) {
        super(owner, balance);
    }

    @Override
    public void calculateInterest() {
        double interest = this.getBalance() * INTEREST_RATE;
        this.setBalance(this.getBalance() + interest);
    }
}
