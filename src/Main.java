import Account.BankAcc;
import Account.CurrentAcc;
import Account.StudentAcc;
import people.AccountOwner;

import java.util.ArrayList;
import java.util.List;

public class Main {//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
    void main() {
        AccountOwner owner = new AccountOwner("Iryna", "Pukas");
        List<BankAcc> accounts = new ArrayList<>();
        BankAcc bankaccount = new CurrentAcc(owner, 1000);

        BankAcc studentaccount = new StudentAcc(owner, 500);
accounts.add(studentaccount);

for (BankAcc account : accounts) {
    if(account instanceof StudentAcc){
        StudentAcc overrideAccount = (StudentAcc) account;
        }

    }
bankaccount.add(10000);
    }

}
