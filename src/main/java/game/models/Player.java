package game.models;

public class Player {
    private int name;

    public Player() {

    }

    public Player(int name) {
        this.name = name;
    }

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }

    @Override
    public String toString() {
        final String GREEN = "\u001B[32m";
        final String WHITE = "\u001B[38m";

        return GREEN + "\nPlayer: " + WHITE + name;
    }
}
