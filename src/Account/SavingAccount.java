package Account;

import people.Owner;

public class SavingAccount extends BankAccount {
    private static final double INTEREST_RATE = 0.02; // 2 %

    private String accounttype = "Saving Account";
    public SavingAccount(Owner owner) {
        super(owner);
    }
    public SavingAccount(Owner owner, double balance) {
        super(owner, balance);
    }

    public SavingAccount(Owner owner, String accountnum) {
        super(owner, accountnum, 0);
    }

    public void add(double amount){
        System.out.println("Add amount: " + amount);
        amount = amount + (amount * 0.05);
double newBalance = this.getBalance() + amount;

        this.setBalance(newBalance);

    }

    @Override
    public void calculateInterest() {
        double interest = this.getBalance() * INTEREST_RATE;
        this.setBalance(this.getBalance() + interest);
    }
}
