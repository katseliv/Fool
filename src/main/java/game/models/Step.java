package game.models;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class Step {
    private final Player target; //на кого нападают (цель)
    LinkedHashMap<Player, HashMap<Card, Card>> list = new LinkedHashMap<>();

    public Step(Player target) {
        this.target = target;
    }

    public Player getTarget() {
        return target;
    }

    public LinkedHashMap<Player, HashMap<Card, Card>> getList() {
        return list;
    }

    @Override
    public String toString() {
        final String BLUE = "\u001B[34m";
        return BLUE + "Step {" + "\ntarget = " + target + ", \nlist = " + list + '}';
    }
}
