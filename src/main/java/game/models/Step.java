package game.models;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Step {
    private Player target;
    private Map<Player, HashMap<Card, Card>> list = new LinkedHashMap<>();

    public Step(Player target) {
        this.target = target;
    }

    public Step(){
        this(null);
    }

    public Player getTarget() {
        return target;
    }

    public void setTarget(Player target) {
        this.target = target;
    }

    public Map<Player, HashMap<Card, Card>> getList() {
        return list;
    }

    public void setList(Map<Player, HashMap<Card, Card>> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        final String BLUE = "\u001B[34m";
        final String WHITE = "\u001B[38m";

        return BLUE + "\nStep" + WHITE + "{" + BLUE + "\ntarget = " + target + ", " + BLUE + "\nlist = " + WHITE + list + '}';
    }
}
