import Account.*;
import accounts.AccountCreationService.AccountRequest;
import di.Conteiner;
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

        Conteiner container = new Conteiner();

        Owner accountOwner = container.ownerService.createOwner("Iryna", "Pukas");

        // --- Dekorátory služby pro vytváření účtů ---
        System.out.println("ORIGINAL");
        container.bankAccountService.createCurrentAccount(accountOwner, 500);
        System.out.println("========");

        System.out.println("WITH LOGGER");
        container.bankAccountWithLoggerService.createStudentAccount(accountOwner, 500, "Delta");
        System.out.println("========");

        System.out.println("WITH TIME LOGGER");
        container.bankAccountWithTimeLoggerService.createSavingAccount(accountOwner);
        System.out.println("========");

        System.out.println("WITH LOGGER AND TIME LOGGER");
        container.bankAccountWithLoggerAndTimeLoggerService.createBusinessAccount(accountOwner, 500);
        System.out.println("========");

        System.out.println("Accounts in service: " + container.bankAccountService.getBankAccounts().size());

        BankAccountFactory bankAccountFactory = container.bankAccountFactory;

        List<BankAccount> createdAccounts = container.accountCreationService.createAccounts(
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

            container.statementService.printBalance(account);

        }


        container.statementService.printBalance(bankAccount);

        TransferLoggerService transferLoggerService = container.transferLoggerService;

        DepositTransferService depositTransferService = container.depositTransferService;
        depositTransferService.deposit(bankAccount, 400);
        depositTransferService.deposit(bankAccount, 100);
        depositTransferService.deposit(bankAccount, 200);
        depositTransferService.deposit(bankAccount, 600);

        container.statementService.printBalance(bankAccount);

        WithdrawTransferService withdrawTransferService = container.withdrawTransferService;

        withdrawTransferService.withdraw(bankAccount, 300);
        withdrawTransferService.withdraw(bankAccount, 300);

        withdrawTransferService.withdraw(bankAccount, 100);
        withdrawTransferService.withdraw(bankAccount, 50);
        withdrawTransferService.withdraw(bankAccount, 400);

        container.statementService.printBalance(bankAccount);

        // --- Simulace převodu mezi dvěma účty ---
        TransferService transferService = container.transferService;

        // běžný převod bez poplatku
        transferService.transfer(bankAccount, studentAccount, 100);

        // převod z business účtu – odečte se poplatek 0.3 %
        transferService.transfer(businessAccount, studentAccount, 1000);

        container.statementService.printBalance(bankAccount);
        container.statementService.printBalance(studentAccount);
        container.statementService.printBalance(businessAccount);

        BankAccount euroAccount = bankAccountFactory.createCurrentAccount(accountOwner, 300, Mena.EUR);
        BankAccount dollarAccount = bankAccountFactory.createCurrentAccount(accountOwner, 100, Mena.USD);
        container.statementService.printBalance(euroAccount);
        container.statementService.printBalance(dollarAccount);

        transferService.transfer(euroAccount, bankAccount, 10);
        transferService.transfer(bankAccount, dollarAccount, 220);

        container.statementService.printBalance(euroAccount);
        container.statementService.printBalance(bankAccount);
        container.statementService.printBalance(dollarAccount);

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
            container.statementService.printTransaction(transaction);
        }

        System.out.println("Student account transactions:");
        for (Transaction transaction : transferLoggerService.getByAccount(studentAccount.getAccountnum())) {
            container.statementService.printTransaction(transaction);
        }

        // --- XML výpis účtů ---
        var accountXmlService = container.accountXmlService;

        System.out.println("Account XML:");
        System.out.println(accountXmlService.toXml(bankAccount));

        List<BankAccount> allAccounts = new ArrayList<>();
        allAccounts.add(bankAccount);
        allAccounts.add(studentAccount);
        allAccounts.add(savingAccount);
        allAccounts.add(businessAccount);

        System.out.println("All accounts XML:");
        System.out.println(accountXmlService.toXml(allAccounts));

        var accountNumberSerializer = container.accountNumberXmlSerializer;

        System.out.println("Account number XML:");
        System.out.println(accountNumberSerializer.serialize(bankAccount));

        System.out.println("All account numbers XML:");
        System.out.println(accountNumberSerializer.serializeAll(allAccounts));

        // --- JSON výpis čísel účtů ---
        var jsonSerializer = container.accountNumberJsonSerializer;
        var gsonSerializer = container.accountNumberGsonSerializer;

        System.out.println("Account number JSON (manual):");
        System.out.println(jsonSerializer.serialize(bankAccount));
        System.out.println(jsonSerializer.serializeAll(allAccounts));

        System.out.println("Account number JSON (Gson):");
        System.out.println(gsonSerializer.serialize(bankAccount));
        System.out.println(gsonSerializer.serializeAll(container.bankAccountService.getBankAccounts()));

    }
}
