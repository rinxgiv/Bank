package Account;

import people.AccountOwner;

public class BusinessAcc extends BankAcc{
    private String accounttype = "Business Account";
    public BusinessAcc(AccountOwner owner) {
        super(owner);
    }

    public BusinessAcc(AccountOwner owner, double balance) {
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
}
