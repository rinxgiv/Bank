import Account.*;
import people.Owner;
import transfers.TransferService;
import transfers.DepositTransferService;
import transfers.WithdrawTransferService;
import accounts.AccountNumberService;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        Owner accountOwner = new Owner("Tomas", "Pesek");
        accountOwner.setLastname("Pokorny");

        // --- Simulace generování čísla účtu ---
        AccountNumberService accountNumberService = new AccountNumberService();
        String generatedNumber = accountNumberService.generate();
        System.out.println("Generated account number: " + generatedNumber);

        BankAccount bankAccount = new CurrentAccount(accountOwner, generatedNumber, 500);
        BankAccount studentAccount = new StudentAccount(accountOwner, "123", 500, "Delta");
        BankAccount savingAccount = new SavingAccount(accountOwner, "123");


        List<BankAccount> bankAccounts = new ArrayList<>();
        bankAccounts.add(bankAccount);
        bankAccounts.add(studentAccount);


        for (BankAccount account: bankAccounts){
            if (account instanceof InterestPoint) {
                ((InterestPoint)account).calculateInterest();
            }
        }

        for (BankAccount account: bankAccounts){

            if (account instanceof StudentAccount) {
                StudentAccount stdAccount = (StudentAccount) account;
                System.out.println("school: " + stdAccount.getSchoolName());
            }

            System.out.println("balance: " + account.getBalance());

        }


        printBalance(bankAccount);

        DepositTransferService depositTransferService = new DepositTransferService();
        depositTransferService.deposit(bankAccount, 400);
        depositTransferService.deposit(bankAccount, 100);
        depositTransferService.deposit(bankAccount, 200);
        depositTransferService.deposit(bankAccount, 600);

        printBalance(bankAccount);

        WithdrawTransferService withdrawTransferService = new WithdrawTransferService();

        withdrawTransferService.withdraw(bankAccount, 300);
        withdrawTransferService.withdraw(bankAccount, 300);

        withdrawTransferService.withdraw(bankAccount, 100);
        withdrawTransferService.withdraw(bankAccount, 50);
        withdrawTransferService.withdraw(bankAccount, 400);

        printBalance(bankAccount);

        // --- Simulace převodu mezi dvěma účty ---
        TransferService transferService = new TransferService();

        BankAccount businessAccount = new BusinessAccount(accountOwner, 2000);

        // běžný převod bez poplatku
        transferService.transfer(bankAccount, studentAccount, 100);

        // převod z business účtu – odečte se poplatek 0.3 %
        transferService.transfer(businessAccount, studentAccount, 1000);

        printBalance(bankAccount);
        printBalance(studentAccount);
        printBalance(businessAccount);

        // ošetření vstupů
        try {
            transferService.transfer(bankAccount, bankAccount, 50); // stejný účet
        } catch (IllegalArgumentException e) {
            System.out.println("Rejected: " + e.getMessage());
        }
        try {
            transferService.transfer(bankAccount, studentAccount, -50); // záporná částka
        } catch (IllegalArgumentException e) {
            System.out.println("Rejected: " + e.getMessage());
        }
        try {
            transferService.transfer(bankAccount, studentAccount, 1_000_000); // nedostatek prostředků
        } catch (IllegalArgumentException e) {
            System.out.println("Rejected: " + e.getMessage());
        }

    }

    private static void printBalance(BankAccount bankAccount) {
        System.out.println("balance: " + bankAccount.getBalance());
    }
}