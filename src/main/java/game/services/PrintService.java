package game.services;

import game.enums.Condition;
import game.enums.ConditionOfPlayer;
import game.models.Card;
import game.models.CyclicList;
import game.models.GameFool;
import game.models.Player;

public class PrintService {
    private final String RED = "\u001B[31m";
    private final String GREEN = "\u001B[32m";
    private final String BLUE = "\u001B[34m";
    private final String WHITE = "\u001B[38m";

    public void printInitialization(GameFool gameFool, Condition condition){
        switch (condition){
            case CREATE_PLAYERS:
                printPlayers(gameFool.getPlayers(), gameFool.getPlayers().getSize());
                break;
            case DISTRIBUTION:
                System.out.println(BLUE + "\nDistribution of cards: " + gameFool + "\n");
                break;
            case CHOOSE_TRUMP:
                System.out.println("\nTrump = " + gameFool.getTrump());
                break;
        }
    }

    private void printPlayers(CyclicList<Player> list, int amount) {
        int counter = 0;

        for (Player player : list) {
            System.out.print(player);
            counter++;
            if (counter == amount) {
                break;
            }
        }

        System.out.println();
    }

    public void printConditionOfGame(Condition condition) {

        System.out.println();
        switch (condition) {
            case START:
                System.out.println(GREEN + "    PLAY !!!    " + WHITE);
                break;
            case TOSS_UP:
                System.out.println(GREEN + "    TOSS UP!!!    " + WHITE);
                break;
            case NO_BEAT_OFF_ATTACK_CARD:
                System.out.println(RED + "Player couldn't beat off attack cards" + WHITE);
                break;
            case NO_BEAT_OFF:
                System.out.println(RED + "Player couldn't beat off all cards" + WHITE);
                break;
            case GAME_IS_OVER:
                System.out.println(RED + " GAME IS OVER!!! " + WHITE);
                break;
        }
    }

    public void printConditionOfPlayers(ConditionOfPlayer condition, Player player) {
        switch (condition) {
            case ATTACK:
                System.out.println(BLUE + "\nPlayer Attack: " + player.getName() + WHITE);
                break;
            case TARGET:
                System.out.println(BLUE + "Player Target: " + player.getName() + WHITE);
                break;
            case WINNER:
                System.out.println(GREEN + "\nPlayer " + player.getName() + " finished Game" + WHITE + "\n");
                break;
            case FOOL:
                System.out.println(RED + "Fool is Player " + player.getName() + WHITE);
                break;
        }
    }

    public void printProcessOfGame(Condition condition, Player player, Card card) {
        switch (condition) {
            case ATTACK:
                System.out.println("\nAttack: " + card + " from Player " + player.getName());
                break;
            case BEAT_OFF:
                System.out.println("Beat off: " + card + " Player " + player.getName() + "\n");
                break;
        }
    }

}
