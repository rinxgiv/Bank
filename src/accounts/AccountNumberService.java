package accounts;

import java.util.Random;

public class AccountNumberService {

    private static final int ACCOUNT_NUMBER_LENGTH = 10;

    private final Random random = new Random();

    public String generate() {
        StringBuilder accountNumber = new StringBuilder();

        // první číslice nesmí být 0
        accountNumber.append(1 + random.nextInt(9));

        for (int i = 1; i < ACCOUNT_NUMBER_LENGTH; i++) {
            accountNumber.append(random.nextInt(10));
        }

        return accountNumber.toString();
    }

}
