import Account.*;
import accounts.AccountCreationService;
import accounts.AccountCreationService.AccountRequest;
import accounts.AccountXmlService;
import factory.BankAccountFactory;
import notifiers.ConsoleNotifier;
import notifiers.TimeNotifierDecorator;
import people.Owner;
import serializers.AccountNumberXmlSerializer;
import serializers.Serializer;
import transfers.TransferService;
import transfers.DepositTransferService;
import transfers.WithdrawTransferService;
import transfers.TransferLoggerService;
import transactions.Transaction;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        Owner accountOwner = new Owner("Iryna", "Pukas");

        BankAccountFactory bankAccountFactory = new BankAccountFactory();
        AccountCreationService accountCreationService = new AccountCreationService(new TimeNotifierDecorator(new ConsoleNotifier()));

        List<BankAccount> createdAccounts = accountCreationService.createAccounts(
                new AccountRequest("Current Account", () -> bankAccountFactory.createCurrentAccount(accountOwner, 500)),
                new AccountRequest("Student Account", () -> bankAccountFactory.createStudentAccount(accountOwner, 500, "Delta")),
                new AccountRequest("Saving Account", () -> bankAccountFactory.createSavingAccount(accountOwner)),
                new AccountRequest("Business Account", () -> bankAccountFactory.createBusinessAccount(accountOwner, 2000))
        );

        BankAccount bankAccount = createdAccounts.get(0);
        BankAccount studentAccount = createdAccounts.get(1);
        BankAccount savingAccount = createdAccounts.get(2);
        BankAccount businessAccount = createdAccounts.get(3);

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

        // --- XML výpis účtů ---
        AccountXmlService accountXmlService = new AccountXmlService();

        System.out.println("Account XML:");
        System.out.println(accountXmlService.toXml(bankAccount));

        List<BankAccount> allAccounts = new ArrayList<>();
        allAccounts.add(bankAccount);
        allAccounts.add(studentAccount);
        allAccounts.add(savingAccount);
        allAccounts.add(businessAccount);

        System.out.println("All accounts XML:");
        System.out.println(accountXmlService.toXml(allAccounts));

        Serializer accountNumberSerializer = new AccountNumberXmlSerializer();

        System.out.println("Account number XML:");
        System.out.println(accountNumberSerializer.serialize(bankAccount));

        System.out.println("All account numbers XML:");
        System.out.println(accountNumberSerializer.serializeAll(allAccounts));

    }

    private static void printBalance(BankAccount bankAccount) {
        System.out.println("balance: " + bankAccount.getBalance());
    }
}