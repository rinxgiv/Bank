package Account;

import people.Owner;

public class BusinessAccount extends BankAccount {
    private static final double INTEREST_RATE = 0.005; // 0.5 %

    private String accounttype = "Business Account";
    public BusinessAccount(Owner owner) {
        super(owner);
    }

    public BusinessAccount(Owner owner, double balance) {
        super(owner, balance);
    }

    public void sub(double amount){
        System.out.println("Sub amount: " + amount);

        double newBalance = this.getBalance() - amount - (amount * 0.1);

        if (newBalance < 0) {
            throw new RuntimeException("Balance is less than 0");
        }
        this.setBalance(newBalance);

    }

    @Override
    public void calculateInterest() {
        double interest = this.getBalance() * INTEREST_RATE;
        this.setBalance(this.getBalance() + interest);
    }
}
