package game.services;

import game.enums.ConditionOfPlayer;
import game.models.Card;
import game.models.GameFool;
import game.models.Player;
import game.models.Step;

import java.util.*;

public class OperationService {

    public Card attack(GameFool gameFool, Player attackPlayer) {
        Set<Card> cards = gameFool.getRatio().get(attackPlayer);
        Card trump = gameFool.getTrump();
        int minNoTrump = Integer.MAX_VALUE;
        int minTrump = Integer.MAX_VALUE;
        Card cardNoTrump = null, cardTrump = null;

        for (Card card : cards) {
            if (card.getRank().ordinal() < minNoTrump && !isTrump(card, trump)) {
                minNoTrump = card.getRank().ordinal();
                cardNoTrump = card;
            } else if (card.getRank().ordinal() < minTrump) {
                minTrump = card.getRank().ordinal();
                cardTrump = card;
            }
        }

        if (cardNoTrump == null) {
            cards.remove(cardTrump);
            return cardTrump;
        }

        cards.remove(cardNoTrump);
        return cardNoTrump;
    }

    public Card beatOffOneCard(GameFool gamefool, Player target, Card attackCard) {
        Set<Card> remainingCards = gamefool.getRatio().get(target);
        int minNoTrump = Integer.MAX_VALUE;
        int minTrump = Integer.MAX_VALUE;
        Card cardNoTrump = null, cardTrump = null;

        for (Card card : remainingCards) {

            if ((card.getSuit() == attackCard.getSuit())
                    && (card.getRank().ordinal() < minNoTrump)
                    && (card.getRank().ordinal() > attackCard.getRank().ordinal())
            ) {
                minNoTrump = card.getRank().ordinal();
                cardNoTrump = card;
            }

            if (isTrump(card, gamefool.getTrump())
                    && !isTrump(attackCard, gamefool.getTrump())
                    && (card.getRank().ordinal() < minTrump)
            ) {
                minTrump = card.getRank().ordinal();
                cardTrump = card;
            }

        }

        if (cardNoTrump == null) {
            remainingCards.remove(cardTrump);
            return cardTrump;
        }

        remainingCards.remove(cardNoTrump);
        return cardNoTrump;
    }

    public boolean isTrump(Card card, Card trump) {
        return card.getSuit() == trump.getSuit();
    }

    public void giveCards(GameFool gameFool) {
        Map<Player, Set<Card>> ratio = gameFool.getRatio();
        List<Card> cards = gameFool.getCards();
        List<Card> cardsRemoving = new ArrayList<>();

        for (Map.Entry<Player, Set<Card>> playerSetEntry : ratio.entrySet()) {
            int size = gameFool.NUMBER_OF_CARDS - playerSetEntry.getValue().size();

            if (cards.size() - size < 0) {
                size = cards.size();
            }

            if (size > 0) {
                for (int i = 0; i < size; i++) {
                    playerSetEntry.getValue().add(cards.get(i));
                    cardsRemoving.add(cards.get(i));
                }
                cards.removeAll(cardsRemoving);
            }
        }
    }

    public void addSteps(GameFool gameFool, Player playerAttack, Player playerTarget, Card attackCard, Card beatOffCard) {
        List<Step> steps = gameFool.getSteps();
        Step step = new Step(playerTarget);
        HashMap<Card, Card> cardHashMap = new HashMap<>();
        cardHashMap.put(attackCard, beatOffCard);
        step.getList().put(playerAttack, cardHashMap);
        steps.add(step);
    }

    public boolean isFinalStepForPlayer(GameFool gameFool, Player player) {
        Map<Player, Set<Card>> ratio = gameFool.getRatio();
        PrintService printer = new PrintService();

        if (ratio.get(player).size() == 0 && gameFool.getCards().size() == 0 && gameFool.getPlayers().getSize() != 1) {
            printer.printConditionOfPlayers(ConditionOfPlayer.WINNER, player);
            return true;
        }

        return false;
    }

}
