package serializers;

import Account.BankAccount;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class AccountNumberGsonSerializer implements Serializer {

    private final Gson gson = new Gson();

    @Override
    public String serialize(BankAccount account) {
        return gson.toJson(new AccountNumber(account.getAccountnum()));
    }

    @Override
    public String serializeAll(List<BankAccount> accounts) {
        List<AccountNumber> accountNumbers = new ArrayList<>();

        for (BankAccount account : accounts) {
            accountNumbers.add(new AccountNumber(account.getAccountnum()));
        }

        return gson.toJson(accountNumbers);
    }
}
