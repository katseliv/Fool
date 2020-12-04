package game;

import game.models.GameFool;
import game.services.Service;

public class Main {

    public static void main(String[] args) {
        try {
            Service service = new Service();
            service.start(new GameFool(), 6, 36);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

}
