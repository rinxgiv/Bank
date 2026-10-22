package accounts;

import Account.BankAccount;
import notifiers.Notifiers;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class AccountCreationService {

    private final Notifiers notifier;

    public AccountCreationService(Notifiers notifier) {
        this.notifier = notifier;
    }

    public record AccountRequest(String accountType, Supplier<? extends BankAccount> creator) {
    }

    public List<BankAccount> createAccounts(AccountRequest... requests) {
        for (AccountRequest request : requests) {
            notifier.notify("vytvarim ucet " + request.accountType());
        }

        List<BankAccount> accounts = new ArrayList<>();

        for (AccountRequest request : requests) {
            accounts.add(request.creator().get());
        }

        for (BankAccount account : accounts) {
            notifier.notify("vytvoril jsem ucet " + account.getAccountType()
                    + " (" + account.getAccountnum() + ")");
        }

        return accounts;
    }

}
