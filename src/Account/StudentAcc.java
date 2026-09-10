package Account;

import people.AccountOwner;

public class StudentAcc extends BankAcc{
    private String school;

    private String accounttype = "Student Account";

    public StudentAcc(AccountOwner owner) {
        super(owner);
    }

    public StudentAcc(AccountOwner owner, double balance) {
        super(owner, balance);
    }
    public String getschool(){
        return this.school;
    }
    public void sub(double amount){
        System.out.println("Sub amount: " + amount);

        double newBalance = this.getBalance() - amount;

        if (newBalance < -5000) {
            throw new RuntimeException("Balance is less than -5000");
        }
        this.setBalance(newBalance);

    }
}
