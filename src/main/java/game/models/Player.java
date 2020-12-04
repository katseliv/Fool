package game.models;

public class Player {
    private final int name;

    public Player(int name) {
        this.name = name;
    }

    public int getName() {
        return name;
    }

    @Override
    public String toString() {
        final String GREEN = "\u001B[32m";
        final String BLACK = "\u001B[30m";

        return GREEN + "\nPlayer: " + BLACK + name;
    }
}
