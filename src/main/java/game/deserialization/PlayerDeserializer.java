package game.deserialization;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import game.models.Player;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

public class PlayerDeserializer implements JsonDeserializer<Player> {
    @Override
    public Player deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        Player player = new Player();
        String data = jsonElement.getAsString();
        List<String> parts = Arrays.asList(data.split(" "));

        player.setName(Integer.parseInt(parts.get(1)));

        return player;
    }
}
