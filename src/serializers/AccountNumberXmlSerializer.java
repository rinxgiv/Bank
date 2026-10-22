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

    // výchozí serializeAll v rozhraní skládá JSON pole, pro XML ho přepíšeme
    @Override
    public String serializeAll(List<BankAccount> accounts) {
        List<AccountNumber> accountNumbers = new ArrayList<>();

        for (BankAccount account : accounts) {
            accountNumbers.add(new AccountNumber(account.getAccountnum()));
        }

        return xStream.toXML(accountNumbers);
    }

    // do XML jde jen číslo účtu
    private static class AccountNumber {

        private final String accountNumber;

        AccountNumber(String accountNumber) {
            this.accountNumber = accountNumber;
        }
    }

}
