import Account.*;
import factory.BankAccountFactory;
import people.Owner;
import transfers.TransferService;
import transfers.DepositTransferService;
import transfers.WithdrawTransferService;
import transfers.TransferLoggerService;
import transactions.Transaction;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        Owner accountOwner = new Owner("Tomas", "Pesek");
        accountOwner.setLastname("Pokorny");

        BankAccountFactory bankAccountFactory = new BankAccountFactory();

        BankAccount bankAccount = bankAccountFactory.createCurrentAccount(accountOwner, 500);
        BankAccount studentAccount = bankAccountFactory.createStudentAccount(accountOwner, 500, "Delta");
        BankAccount savingAccount = bankAccountFactory.createSavingAccount(accountOwner);
        BankAccount businessAccount = bankAccountFactory.createBusinessAccount(accountOwner, 2000);

        System.out.println("Generated account number: " + bankAccount.getAccountnum());


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

        TransferLoggerService transferLoggerService = new TransferLoggerService();

        DepositTransferService depositTransferService = new DepositTransferService(transferLoggerService);
        depositTransferService.deposit(bankAccount, 400);
        depositTransferService.deposit(bankAccount, 100);
        depositTransferService.deposit(bankAccount, 200);
        depositTransferService.deposit(bankAccount, 600);

        printBalance(bankAccount);

        WithdrawTransferService withdrawTransferService = new WithdrawTransferService(transferLoggerService);

        withdrawTransferService.withdraw(bankAccount, 300);
        withdrawTransferService.withdraw(bankAccount, 300);

        withdrawTransferService.withdraw(bankAccount, 100);
        withdrawTransferService.withdraw(bankAccount, 50);
        withdrawTransferService.withdraw(bankAccount, 400);

        printBalance(bankAccount);

        // --- Simulace převodu mezi dvěma účty ---
        TransferService transferService = new TransferService(transferLoggerService);

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

        // --- Historie transakcí ---
        System.out.println("All transactions:");
        for (Transaction transaction : transferLoggerService.getAll()) {
            System.out.println(transaction);
        }

        System.out.println("Student account transactions:");
        for (Transaction transaction : transferLoggerService.getByAccount(studentAccount.getAccountnum())) {
            System.out.println(transaction);
        }

    }

    private static void printBalance(BankAccount bankAccount) {
        System.out.println("balance: " + bankAccount.getBalance());
    }
}