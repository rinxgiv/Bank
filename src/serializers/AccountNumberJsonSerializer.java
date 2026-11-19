package serializers;

import Account.BankAccount;

/**
 * Ruční JSON serializace bez knihovny; serializeAll používá výchozí implementaci ze Serializer.
 */
public class AccountNumberJsonSerializer implements Serializer {

    @Override
    public String serialize(BankAccount account) {
        return "{\"accountNumber\": \"" + account.getAccountnum() + "\"}";
    }
}
