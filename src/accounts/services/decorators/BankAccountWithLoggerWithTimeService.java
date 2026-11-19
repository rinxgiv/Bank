package accounts.services.decorators;

import accounts.services.IBankAccountService;

import java.time.LocalTime;

public class BankAccountWithLoggerWithTimeService extends BankAccountWithLoggerService {

    public BankAccountWithLoggerWithTimeService(IBankAccountService bankAccountService) {
        super(bankAccountService);
    }

    @Override
    protected void logStart() {
        System.out.println("Create Bank Account started, " + LocalTime.now());
    }

    @Override
    protected void logEnd() {
        System.out.println("Create Bank Account finished, " + LocalTime.now());
    }
}
