package game.models;

import game.enums.CardSuit;
import game.enums.RankOfCards;

public class Card {
    private RankOfCards rank;
    private CardSuit suit;

    public Card(){

    }

    public Card(RankOfCards rank, CardSuit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public RankOfCards getRank() {
        return rank;
    }

    public CardSuit getSuit() {
        return suit;
    }

    public void setRank(RankOfCards rank) {
        this.rank = rank;
    }

    public void setSuit(CardSuit suit) {
        this.suit = suit;
    }

    @Override
    public String toString() {
        final String RED = "\u001B[31m";
        final String BLACK = "\u001B[30m";
        final String WHITE = "\u001B[38m";

        String color = BLACK;
        if (suit == CardSuit.HEARTS || suit == CardSuit.DIAMONDS) {
            color = RED;
        }

        return color + rank.getRank() + " " + suit.getSign() + WHITE;
    }
}
