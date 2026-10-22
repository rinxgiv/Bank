package serializers;

import Account.BankAccount;

public class AccountNumberJsonSerializer implements Serializer {

    @Override
    public String serialize(BankAccount account) {
        StringBuilder json = new StringBuilder();

        json.append("{");
        json.append("\"accountNumber\": ");

        String accountNumber = account.getAccountnum();
        if (accountNumber == null) {
            json.append("null");
        } else {
            json.append("\"").append(accountNumber).append("\"");
        }

        json.append("}");

        return json.toString();
    }

}
