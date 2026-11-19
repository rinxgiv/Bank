package Account;

import notifiers.Notifiers;
import people.Owner;

public abstract class BankAccount implements InterestPoint {

    private String uuid;

    private Owner owner;

    private double balance;

    protected Notifiers notifier;

private String accountnum;

    public BankAccount(Owner owner) {
        this.owner = owner;
        this.balance = 0;
    }

    public BankAccount(Owner owner, double balance) {
        this.owner = owner;
        this.balance = balance;
    }

    public BankAccount(Owner owner, String accountnum, double balance) {
        this.owner = owner;
        this.accountnum = accountnum;
        this.balance = balance;
    }



    public abstract String getAccountType();

    public void setNotifier(Notifiers notifier) {
        this.notifier = notifier;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getAccountnum() {
        return accountnum;
    }

    public void setAccountnum(String accountnum) {
        this.accountnum = accountnum;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
    public void sub(double amount){
        this.notifier.notify("Sub amount: " + amount);

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
