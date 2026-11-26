package Account;

public enum Mena {
    CZK("Kč"),
    USD("$"),
    EUR("€");

    private final String symbol;

    Mena(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}
