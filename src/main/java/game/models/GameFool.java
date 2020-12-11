package game.models;

import java.util.*;

public class GameFool {
    private Card trump;
    private boolean isMissTurn = false;
    private Player playerAttack = null;
    private Player playerTarget = null;
    private Player playerFool = null;
    public transient final int NUMBER_OF_CARDS = 6;
    public transient final int NUMBER_CARDS_FOR_TOSS_UP = NUMBER_OF_CARDS - 1;
    private final CyclicList<Player> players = new CyclicList<>();
    private final List<Card> cardsOnTheTable = new ArrayList<>();
    private Map<Player, Set<Card>> ratio = new LinkedHashMap<>();
    private List<Card> cards = new ArrayList<>();
    private List<Step> steps = new ArrayList<>();

    public CyclicList<Player> getPlayers() {
        return players;
    }

    public List<Card> getCardsOnTheTable() {
        return cardsOnTheTable;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public List<Step> getSteps() {
        return steps;
    }

    public void setSteps(List<Step> steps) {
        this.steps = steps;
    }

    public Map<Player, Set<Card>> getRatio() {
        return ratio;
    }

    public void setRatio(Map<Player, Set<Card>> ratio) {
        this.ratio = ratio;
    }

    public Card getTrump() {
        return trump;
    }

    public void setTrump(Card trump) {
        this.trump = trump;
    }

    public Player getPlayerAttack() {
        return playerAttack;
    }

    public void setPlayerAttack(Player playerAttack) {
        this.playerAttack = playerAttack;
    }

    public Player getPlayerTarget() {
        return playerTarget;
    }

    public void setPlayerTarget(Player playerTarget) {
        this.playerTarget = playerTarget;
    }

    public Player getPlayerFool() {
        return playerFool;
    }

    public void setPlayerFool(Player playerFool) {
        this.playerFool = playerFool;
    }

    public boolean isMissTurn() {
        return isMissTurn;
    }

    public void setMissTurn(boolean missTurn) {
        isMissTurn = missTurn;
    }

    @Override
    public String toString() {
        final String PURPLE = "\u001B[35m";
        final String WHITE = "\u001B[38m";

        return PURPLE + " \nGameFool " + WHITE + "{" + PURPLE
                + "\nplayers = " + WHITE + players + PURPLE
                + ",\ncards = " + WHITE + cards + PURPLE
                + "\nlength = " + cards.size()
                + ", ratio = " + WHITE + ratio + PURPLE;
//                + ", steps = " + steps + '}';
    }
}
