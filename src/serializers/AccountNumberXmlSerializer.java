package serializers;

import Account.BankAccount;
import com.thoughtworks.xstream.XStream;

import java.util.ArrayList;
import java.util.List;

public class AccountNumberXmlSerializer implements Serializer {

    private final XStream xStream = new XStream();

    public AccountNumberXmlSerializer() {
        xStream.alias("account", AccountNumber.class);
        xStream.alias("accounts", List.class);
    }

    @Override
    public String serialize(BankAccount account) {
        return xStream.toXML(new AccountNumber(account.getAccountnum()));
    }

    @Override
    public String serializeAll(List<BankAccount> accounts) {
        List<AccountNumber> accountNumbers = new ArrayList<>();

        for (BankAccount account : accounts) {
            accountNumbers.add(new AccountNumber(account.getAccountnum()));
        }

        return xStream.toXML(accountNumbers);
    }
}
