package game.enums;

public enum CardSuit {
    CLUBS("\u001B[30m\u2663"),
    DIAMONDS("\u001B[31m\u2666"),
    SPADES("\u001B[30m\u2660"),
    HEARTS("\u001B[31m\u2764");

    private final String sign;

    CardSuit(String sign) {
        this.sign = sign;
    }

    public String getSign() {
        return sign;
    }

    public static final CardSuit[] CARD_SUIT = new CardSuit[CardSuit.values().length];

    static {
        for (int i = 0; i < CardSuit.values().length; i++)
            CARD_SUIT[i] = CardSuit.values()[i];
    }
}
