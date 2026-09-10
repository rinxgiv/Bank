package Account;

import people.AccountOwner;

public class CurrentAcc extends BankAcc{
    private String accounttype = "Current Account";

    public CurrentAcc(AccountOwner owner) {
        super(owner);
    }

    public CurrentAcc(AccountOwner owner, double balance) {
        super(owner, balance);
    }
    @Override
    public void sub(double amount){
        System.out.println("Sub amount: " + amount);

        double newBalance = this.getBalance() - amount;

        if (newBalance < 0) {
            throw new RuntimeException("Balance is negative");
        }
        this.setBalance(newBalance);

    }
}
