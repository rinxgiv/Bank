package Account;

import people.AccountOwner;

public class SavingAcc extends BankAcc{
    private String accounttype = "Saving Account";
    public SavingAcc(AccountOwner owner) {
        super(owner);
    }
    public SavingAcc(AccountOwner owner, double balance) {
        super(owner, balance);
    }

    public void add(double amount){
        System.out.println("Add amount: " + amount);
        amount = amount + (amount * 0.05);
double newBalance = this.getBalance() + amount;

        this.setBalance(newBalance);

    }
}
