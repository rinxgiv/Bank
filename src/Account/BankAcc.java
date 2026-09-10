package Account;

import people.AccountOwner;

public abstract class BankAcc {

    private String uuid;

    private AccountOwner owner;

    private double balance;

private String accountnum;

    public BankAcc(AccountOwner owner) {
        this.owner = owner;
        this.balance = 0;
    }

    public BankAcc(AccountOwner owner, double balance) {
        this.owner = owner;
        this.balance = balance;
    }



    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
    public void sub(double amount){
        System.out.println("Sub amount: " + amount);

        double newBalance = this.balance - amount;

        if (newBalance < 0) {
        throw new RuntimeException("Balance is negative");
        }
        this.balance=newBalance;

    }
   public void add(double amount){
       System.out.println("Add amount: " + amount);
        this.balance=this.balance+amount;
    }


}
