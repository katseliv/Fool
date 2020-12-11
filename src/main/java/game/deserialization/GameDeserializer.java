package game.deserialization;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import game.models.Card;
import game.models.GameFool;
import game.models.Player;
import game.models.Step;
import java.lang.reflect.Type;
import java.util.*;

public class GameDeserializer implements JsonDeserializer<GameFool> {

    @Override
    public GameFool deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();

        GameFool gameFool = new GameFool();
        gameFool.setTrump(context.deserialize(jsonObject.get("Trump"), Card.class));
        gameFool.setMissTurn(jsonObject.get("Miss Turn").getAsBoolean());
        gameFool.setPlayerAttack(context.deserialize(jsonObject.get("Attack Player"), Player.class));
        gameFool.setPlayerTarget(context.deserialize(jsonObject.get("Target Player"), Player.class));
        gameFool.setPlayerFool(context.deserialize(jsonObject.get("Fool Player"), Player.class));

        JsonArray cards = jsonObject.getAsJsonArray("Cards");
        for (JsonElement card : cards) {
            gameFool.getCards().add(context.deserialize(card, Card.class));

        }

        JsonArray players = jsonObject.getAsJsonArray("Players");
        for (JsonElement player : players) {
            gameFool.getPlayers().add(context.deserialize(player, Player.class));
        }

        JsonArray cardsOnTheTable = jsonObject.getAsJsonArray("Cards on the table");
        for (JsonElement card : cardsOnTheTable) {
            gameFool.getCardsOnTheTable().add(context.deserialize(card, Card.class));

        }

        Type typeToken1 = new TypeToken<Map<Player, Set<Card>>>() {
        }.getType();
        gameFool.setRatio(context.deserialize(jsonObject.get("Ratio"), typeToken1));

        Type typeToken2 = new TypeToken<List<Step>>() {
        }.getType();
        gameFool.setSteps(context.deserialize(jsonObject.get("Steps"), typeToken2));

        return gameFool;
    }

}
