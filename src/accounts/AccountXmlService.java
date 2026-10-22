package accounts;

import Account.BankAccount;
import Account.BusinessAccount;
import Account.CurrentAccount;
import Account.SavingAccount;
import Account.StudentAccount;
import com.thoughtworks.xstream.XStream;
import people.Owner;

import java.util.List;

public class AccountXmlService {

    // XStream funguje podobně jako Gson - serializuje objekt podle jeho fieldů
    private final XStream xStream = new XStream();

    public AccountXmlService() {
        // hezčí názvy elementů místo plných názvů tříd (např. <Account.CurrentAccount>)
        xStream.alias("currentAccount", CurrentAccount.class);
        xStream.alias("studentAccount", StudentAccount.class);
        xStream.alias("savingAccount", SavingAccount.class);
        xStream.alias("businessAccount", BusinessAccount.class);
        xStream.alias("owner", Owner.class);
        xStream.alias("accounts", List.class);

        // stejný owner u více účtů vypíše celý (jako Gson), ne jako <owner reference="..."/>
        xStream.setMode(XStream.NO_REFERENCES);

        // notifier je chování, ne data účtu
        xStream.omitField(BankAccount.class, "notifier");

        // pro případné čtení XML povolíme jen naše třídy (bezpečnost)
        xStream.allowTypes(new Class[]{
                CurrentAccount.class, StudentAccount.class, SavingAccount.class, BusinessAccount.class, Owner.class
        });
    }

    // jeden účet
    public String toXml(BankAccount account) {
        return xStream.toXML(account);
    }

    // všechny účty
    public String toXml(List<BankAccount> accounts) {
        return xStream.toXML(accounts);
    }

}
