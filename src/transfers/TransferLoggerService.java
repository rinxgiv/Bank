package transfers;

import transactions.Transaction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TransferLoggerService {

    private final List<Transaction> transactions = new ArrayList<>();

    // číslo účtu -> transakce, kterých se účet účastnil
    private final Map<String, List<Transaction>> transactionsByAccount = new HashMap<>();

    public void log(Transaction transaction) {
        transactions.add(transaction);

        if (transaction.getFromAccountNum() != null) {
            addToAccount(transaction.getFromAccountNum(), transaction);
        }
        if (transaction.getToAccountNum() != null) {
            addToAccount(transaction.getToAccountNum(), transaction);
        }
    }

    public List<Transaction> getAll() {
        return new ArrayList<>(transactions);
    }

    public List<Transaction> getByAccount(String accountNum) {
        return new ArrayList<>(transactionsByAccount.getOrDefault(accountNum, new ArrayList<>()));
    }

    private void addToAccount(String accountNum, Transaction transaction) {
        transactionsByAccount.computeIfAbsent(accountNum, key -> new ArrayList<>()).add(transaction);
    }

}
