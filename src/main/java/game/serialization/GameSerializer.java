package game.serialization;

import com.google.gson.*;
import game.models.GameFool;
import java.lang.reflect.Type;

public class GameSerializer implements JsonSerializer<GameFool> {

    @Override
    public JsonElement serialize(GameFool gameFool, Type type, JsonSerializationContext context) {
        JsonObject result = new JsonObject();

        result.add("Trump", context.serialize(gameFool.getTrump()));
        result.add("Miss Turn", context.serialize(gameFool.isMissTurn()));
        result.add("Attack Player", context.serialize(gameFool.getPlayerAttack()));
        result.add("Target Player", context.serialize(gameFool.getPlayerTarget()));
        result.add("Fool Player", context.serialize(gameFool.getPlayerTarget()));
        result.add("Players", context.serialize(gameFool.getPlayers().toArray()));
        result.add("Cards", context.serialize(gameFool.getCards()));
        result.add("Cards on the table", context.serialize(gameFool.getCardsOnTheTable()));
        result.add("Ratio", context.serialize(gameFool.getRatio()));
        result.add("Steps", context.serialize(gameFool.getSteps()));

        return result;
    }

}
