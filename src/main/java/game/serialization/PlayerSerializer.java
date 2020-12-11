package game.serialization;

import com.google.gson.*;
import game.models.Player;

import java.lang.reflect.Type;

public class PlayerSerializer implements JsonSerializer<Player> {
    @Override
    public JsonElement serialize(Player player, Type type, JsonSerializationContext context) {
        return new JsonPrimitive("Player " + player.getName());
    }
}
