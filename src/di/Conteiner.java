package di;

import Account.Mena;
import accounts.AccountCreationService;
import accounts.AccountXmlService;
import accounts.services.AccountNumberGenerator;
import accounts.services.BankAccountService;
import accounts.services.IBankAccountService;
import accounts.services.StatementService;
import accounts.services.decorators.BankAccountWithLoggerService;
import accounts.services.decorators.BankAccountWithLoggerWithTimeService;
import currency.ExchangeRateService;
import currency.MoneyFormatter;
import factory.BankAccountFactory;
import factory.TransactionFactory;
import notifiers.ConsoleNotifier;
import notifiers.Notifiers;
import notifiers.TimeNotifierDecorator;
import people.factories.OwnerFactory;
import people.services.OwnerService;
import serializers.AccountNumberGsonSerializer;
import serializers.AccountNumberJsonSerializer;
import serializers.AccountNumberXmlSerializer;
import serializers.Serializer;
import transfers.DepositTransferService;
import transfers.TransferLoggerService;
import transfers.TransferService;
import transfers.WithdrawTransferService;

import java.util.Map;

public class Conteiner {
    public Notifiers notifier = new TimeNotifierDecorator(new ConsoleNotifier());

    public Mena menaUctu = Mena.CZK;
    public Mena prepocetNa = Mena.USD;

    public ExchangeRateService exchangeRateService = new ExchangeRateService(Map.of(
            Mena.USD, 22.0,
            Mena.EUR, 25.0
    ));
    public MoneyFormatter moneyFormatter = new MoneyFormatter(exchangeRateService, prepocetNa, menaUctu);
    public StatementService statementService = new StatementService(moneyFormatter);

    public OwnerFactory ownerFactory = new OwnerFactory();
    public OwnerService ownerService = new OwnerService(ownerFactory);

    public AccountNumberGenerator accountNumberGenerator = new AccountNumberGenerator("0800");
    public BankAccountFactory bankAccountFactory = new BankAccountFactory(accountNumberGenerator, notifier, menaUctu);
    public AccountCreationService accountCreationService = new AccountCreationService(notifier);

    public IBankAccountService bankAccountService = new BankAccountService(bankAccountFactory);
    public IBankAccountService bankAccountWithLoggerService = new BankAccountWithLoggerService(bankAccountService);
    public IBankAccountService bankAccountWithTimeLoggerService = new BankAccountWithLoggerWithTimeService(bankAccountService);
    public IBankAccountService bankAccountWithLoggerAndTimeLoggerService =
            new BankAccountWithLoggerService(bankAccountWithTimeLoggerService);

    public TransactionFactory transactionFactory = new TransactionFactory();
    public TransferLoggerService transferLoggerService = new TransferLoggerService();
    public DepositTransferService depositTransferService =
            new DepositTransferService(transferLoggerService, transactionFactory);
    public WithdrawTransferService withdrawTransferService =
            new WithdrawTransferService(transferLoggerService, transactionFactory);
    public TransferService transferService = new TransferService(transferLoggerService, transactionFactory, exchangeRateService);

    public AccountXmlService accountXmlService = new AccountXmlService();
    public Serializer accountNumberXmlSerializer = new AccountNumberXmlSerializer();
    public Serializer accountNumberJsonSerializer = new AccountNumberJsonSerializer();
    public Serializer accountNumberGsonSerializer = new AccountNumberGsonSerializer();
}
