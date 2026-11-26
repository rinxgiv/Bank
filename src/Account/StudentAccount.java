package Account;

import people.Owner;

public class StudentAccount extends BankAccount {
    private static final double INTEREST_RATE = 0.015; // 1.5 %

    private String school;

    private String accounttype = "Student Account";

    @Override
    public String getAccountType() {
        return accounttype;
    }

    public StudentAccount(Owner owner) {
        super(owner);
    }

    public StudentAccount(Owner owner, double balance) {
        super(owner, balance);
    }

    public StudentAccount(Owner owner, String accountnum, double balance, String school) {
        super(owner, accountnum, balance);
        this.school = school;
    }
    public String getSchoolName(){
        return this.school;
    }

    @Override
    public void calculateInterest() {
        double interest = this.getBalance() * INTEREST_RATE;
        this.setBalance(this.getBalance() + interest);
    }
}
