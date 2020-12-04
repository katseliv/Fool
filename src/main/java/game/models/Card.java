package game.models;

import game.enums.CardSuit;
import game.enums.RankOfCards;

public class Card {
    private final RankOfCards rank;
    private final CardSuit suit;

    public Card(RankOfCards id, CardSuit suit) {
        this.rank = id;
        this.suit = suit;
    }

    public RankOfCards getRank() {
        return rank;
    }

    public CardSuit getSuit() {
        return suit;
    }

    @Override
    public String toString() {
        final String RED = "\u001B[31m";
        final String BLACK = "\u001B[30m";

        String color = BLACK;
        if (suit == CardSuit.HEARTS || suit == CardSuit.DIAMONDS) {
            color = RED;
        }

        return "\n" + BLACK + "Rank = " + color + rank.getRank() + BLACK + " Suit = " + color + suit.getSign() + BLACK;
    }
}
