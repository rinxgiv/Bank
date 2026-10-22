package serializers;

import Account.BankAccount;

import java.util.List;

public interface Serializer {

    String serialize(BankAccount account);

    default String serializeAll(List<BankAccount> accounts) {
        StringBuilder json = new StringBuilder();

        json.append("[\n");

        for (int i = 0; i < accounts.size(); i++) {
            json.append("  ").append(serialize(accounts.get(i)));

            if (i < accounts.size() - 1) {
                json.append(",");
            }
            json.append("\n");
        }

        json.append("]");

        return json.toString();
    }

}
